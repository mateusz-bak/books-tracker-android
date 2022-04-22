package software.mdev.bookstracker.adapters

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.BitmapFactory
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.item_book.view.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import software.mdev.bookstracker.R
import software.mdev.bookstracker.data.db.entities.Book
import software.mdev.bookstracker.other.Constants
import java.text.SimpleDateFormat
import java.util.*

class BookAdapter(
    var context: Context,
    private val whichFragment: String,
    private val showTags: Boolean

) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {
    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent,false))
    }

    private var onBookClickListener: ((Book) -> Unit)? = null
    private var onBookLongClickListener: ((Book) -> Unit)? = null

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val curBook = differ.currentList[position]
        holder.itemView.apply {
            tvBookTitle.text = curBook.bookTitle
            tvBookAuthor.text = curBook.bookAuthor

            var stringPages = curBook.bookNumberOfPages.toString() +
                    holder.itemView.getContext().getString(R.string.space) +
                    holder.itemView.getContext().getString(R.string.pages)

            tvNumberOfPages.text = stringPages
            tvNumberOfPages.visibility = View.GONE

            tvDateStarted.visibility = View.GONE
            tvDateStartedTitle.visibility = View.GONE

            tvDateFinished.visibility = View.GONE
            tvDateFinishedTitle.visibility = View.GONE

            if(curBook.bookStartDate == "none" || curBook.bookStartDate == "null") {
                tvDateStarted.text = holder.itemView.getContext().getString(R.string.not_set)
            } else {
                var bookStartTimeStampLong = curBook.bookStartDate.toLong()
                tvDateStarted.text = convertLongToTime(bookStartTimeStampLong)
            }

            if(curBook.bookFinishDate == "none" || curBook.bookFinishDate == "null") {
                tvDateFinished.text = holder.itemView.getContext().getString(R.string.not_set)
            } else {
                var bookFinishTimeStampLong = curBook.bookFinishDate.toLong()
                tvDateFinished.text = convertLongToTime(bookFinishTimeStampLong)
            }

            var sharedPreferencesName = holder.itemView.getContext().getString(R.string.shared_preferences_name)
            val sharedPref = context.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE)

            val sortOrder = sharedPref.getString(
                Constants.SHARED_PREFERENCES_KEY_SORT_ORDER,
                Constants.SORT_ORDER_TITLE_ASC
            )

            if (sortOrder != null) {
                setViewsAccordingToSort(this, sortOrder)
            }

            when (whichFragment ){
                Constants.BOOK_STATUS_READ -> rbRatingIndicator.rating = curBook.bookRating
            }
            when (curBook.bookStatus ){
                Constants.BOOK_STATUS_READ -> {
                    rbRatingIndicator.visibility = View.VISIBLE
                    rbRatingIndicator.rating = curBook.bookRating
                }
                Constants.BOOK_STATUS_IN_PROGRESS -> {
                    rbRatingIndicator.visibility = View.GONE

                    tvDateFinished.visibility = View.GONE
                    tvDateFinishedTitle.visibility = View.GONE
                }
                Constants.BOOK_STATUS_TO_READ -> {
                    rbRatingIndicator.visibility = View.GONE
                    tvNumberOfPages.visibility = View.GONE

                    tvDateStarted.visibility = View.GONE
                    tvDateStartedTitle.visibility = View.GONE

                    tvDateFinished.visibility = View.GONE
                    tvDateFinishedTitle.visibility = View.GONE
                }
            }

            setCover (this, curBook.bookCoverImg)

            setTagsVisibility(showTags, holder.itemView)
            MainScope().launch {
                val tags = getTags(curBook.bookTags)
                setTags(tags, holder.itemView)
            }
        }

        holder.itemView.apply {
            setOnClickListener {
                onBookClickListener?.let { it(curBook) }
            }
            setOnLongClickListener {
                onBookLongClickListener?.let { it(curBook) }
                true
            }
        }
    }

    private fun setCover(view: View, bookCoverImg: ByteArray?) {
        if (bookCoverImg == null) {
            view.ivBookCover.visibility = View.GONE

            val tvBookTitleLayout = view.tvBookTitle.layoutParams as ConstraintLayout.LayoutParams
            tvBookTitleLayout.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
            tvBookTitleLayout.marginStart = 0

            view.tvBookTitle.layoutParams = tvBookTitleLayout
        } else {
            view.ivBookCover.visibility = View.VISIBLE

            val tvBookTitleLayout = view.tvBookTitle.layoutParams as ConstraintLayout.LayoutParams
            tvBookTitleLayout.topToTop = view.ivBookCover.id
            tvBookTitleLayout.marginStart = 50

            view.tvBookTitle.layoutParams = tvBookTitleLayout

            val bmp = BitmapFactory.decodeByteArray(bookCoverImg, 0, bookCoverImg.size)
            view.ivBookCover.setImageBitmap(bmp)
        }
    }

    private fun setViewsAccordingToSort(view: View, sortOrder: String) {
        when (sortOrder) {
            Constants.SORT_ORDER_PAGES_DESC -> {
                view.tvNumberOfPages.visibility = View.VISIBLE
                view.tvDateStarted.visibility = View.GONE
                view.tvDateStartedTitle.visibility = View.GONE
                view.tvDateFinished.visibility = View.GONE
                view.tvDateFinishedTitle.visibility = View.GONE
            }
            Constants.SORT_ORDER_PAGES_ASC -> {
                view.tvNumberOfPages.visibility = View.VISIBLE
                view.tvDateStarted.visibility = View.GONE
                view.tvDateStartedTitle.visibility = View.GONE
                view.tvDateFinished.visibility = View.GONE
                view.tvDateFinishedTitle.visibility = View.GONE
            }
            Constants.SORT_ORDER_START_DATE_DESC -> {
                view.tvNumberOfPages.visibility = View.GONE
                view.tvDateStarted.visibility = View.VISIBLE
                view.tvDateStartedTitle.visibility = View.VISIBLE
                view.tvDateFinished.visibility = View.GONE
                view.tvDateFinishedTitle.visibility = View.GONE
            }
            Constants.SORT_ORDER_START_DATE_ASC -> {
                view.tvNumberOfPages.visibility = View.GONE
                view.tvDateStarted.visibility = View.VISIBLE
                view.tvDateStartedTitle.visibility = View.VISIBLE
                view.tvDateFinished.visibility = View.GONE
                view.tvDateFinishedTitle.visibility = View.GONE
            }
            Constants.SORT_ORDER_FINISH_DATE_DESC -> {
                view.tvNumberOfPages.visibility = View.GONE
                view.tvDateStarted.visibility = View.GONE
                view.tvDateStartedTitle.visibility = View.GONE
                view.tvDateFinished.visibility = View.VISIBLE
                view.tvDateFinishedTitle.visibility = View.VISIBLE
            }
            Constants.SORT_ORDER_FINISH_DATE_ASC -> {
                view.tvNumberOfPages.visibility = View.GONE
                view.tvDateStarted.visibility = View.GONE
                view.tvDateStartedTitle.visibility = View.GONE
                view.tvDateFinished.visibility = View.VISIBLE
                view.tvDateFinishedTitle.visibility = View.VISIBLE
            }
        }
    }

    fun setOnBookClickListener(listener: (Book) -> Unit) {
        onBookClickListener = listener
    }

    fun setOnBookLongClickListener(listener: (Book) -> Unit) {
        onBookLongClickListener = listener
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("dd MMM yyyy")
        return format.format(date)
    }

    private fun setTagsVisibility(showTags: Boolean, itemView: View) {
        if (showTags) {
            itemView.clBookTags.visibility = View.VISIBLE
        } else {
            itemView.clBookTags.visibility = View.GONE
        }
    }

    private fun getTags(tags: List<String>?): List<String>? {
        return if (tags != null && tags.isNotEmpty()) {
            tags.sortedWith(String.CASE_INSENSITIVE_ORDER)
        } else
            null
    }

    private fun setTags(tags: List<String>?, itemView: View) {
        if (tags != null) {
            itemView.cgTags.removeAllViews()

            for (tag in tags) {
                val chip = Chip(context)
                chip.text = tag
                chip.isClickable = false
                chip.isCheckable = false
                chip.chipBackgroundColor = ColorStateList.valueOf(getThemeAccentColor(context))
                chip.setTextColor(context.getColor(R.color.colorDefaultBg))
                chip.ensureAccessibleTouchTarget(0)
                chip.setEnsureMinTouchTargetSize(false)
                chip.isContextClickable = false
                chip.chipStartPadding = 0F
                chip.chipEndPadding = 0F
                chip.textStartPadding = 10F
                chip.textEndPadding = 10F
                chip.textSize = 12F
                chip.minimumWidth = 0
                chip.textAlignment = View.TEXT_ALIGNMENT_CENTER

                itemView.cgTags.addView(chip as View)
            }
        }
    }

    private fun getThemeAccentColor(context: Context): Int {
        val value = TypedValue()
        context.theme.resolveAttribute(R.attr.colorAccent, value, true)
        return value.data
    }
}