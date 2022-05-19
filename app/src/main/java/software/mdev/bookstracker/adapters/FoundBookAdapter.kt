package software.mdev.bookstracker.adapters

import android.content.Context
import android.content.res.ColorStateList
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.squareup.picasso.Picasso
import software.mdev.bookstracker.R
import kotlinx.android.synthetic.main.item_book_searched_by_olid.view.*
import software.mdev.bookstracker.api.models.OpenLibraryOLIDResponse
import software.mdev.bookstracker.other.Resource
import software.mdev.bookstracker.other.RoundCornersTransform
import software.mdev.bookstracker.ui.bookslist.viewmodel.BooksViewModel

class FoundBookAdapter(
    private val viewModel: BooksViewModel
) : RecyclerView.Adapter<FoundBookAdapter.OpenLibraryBookViewHolder>() {
    inner class OpenLibraryBookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback =
        object : DiffUtil.ItemCallback<Resource<OpenLibraryOLIDResponse>>() {
            override fun areItemsTheSame(
                oldItem: Resource<OpenLibraryOLIDResponse>,
                newItem: Resource<OpenLibraryOLIDResponse>
            ): Boolean {
                return oldItem.data?.key == newItem.data?.key
            }

            override fun areContentsTheSame(
                oldItem: Resource<OpenLibraryOLIDResponse>,
                newItem: Resource<OpenLibraryOLIDResponse>
            ): Boolean {
                return oldItem == newItem
            }
        }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OpenLibraryBookViewHolder {
        return OpenLibraryBookViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_book_searched_by_olid, parent, false)
        )
    }

    private var onBookClickListener: ((Resource<OpenLibraryOLIDResponse>) -> Unit)? = null

    override fun onBindViewHolder(holder: OpenLibraryBookViewHolder, position: Int) {
        val curBook = differ.currentList[position]

        holder.itemView.apply {

            if (curBook != null) {

                if (curBook.data != null) {

                    if (curBook.data.title != null) {
                        val title = curBook.data.title
                        tvBookTitle.text = title
                    }

                    if (curBook.data.authors != null) {
                        var allAuthors = ""
                        for (author in curBook.data.authors) {
                            var key = author.key

                            allAuthors += key
                            allAuthors += ", "

                            viewModel.showLoadingCircle.postValue(false)

                        }

                        if (allAuthors.isNotEmpty()) {
                            if (allAuthors.last().toString() == " ")
                                allAuthors = allAuthors.dropLast(1)

                            if (allAuthors.last().toString() == ",")
                                allAuthors = allAuthors.dropLast(1)
                        }

                        tvBookAuthor.text = allAuthors
                    } else {
                        tvBookAuthor.text = holder.itemView.context.getString(R.string.unknown_author)
                    }

                    if (curBook.data.covers != null && curBook.data.covers!!.isNotEmpty()) {
                        viewModel.showLoadingCircle.postValue(true)
                        ivBookCover.setImageTintList(null)
                        val circularProgressDrawable = CircularProgressDrawable(this.context)
                        circularProgressDrawable.strokeWidth = 5f
                        circularProgressDrawable.centerRadius = 30f
                        circularProgressDrawable.setColorSchemeColors(
                            ContextCompat.getColor(
                                context,
                                R.color.grey
                            )
                        )
                        circularProgressDrawable.start()

                        var coverID = curBook.data.covers!![0]
                        var coverUrl = "https://covers.openlibrary.org/b/id/$coverID-M.jpg"

                        viewModel.showLoadingCircle.postValue(true)

                        Picasso
                            .get()
                            .load(coverUrl)
                            .placeholder(circularProgressDrawable)
                            .error(R.drawable.ic_iconscout_exclamation_octagon_24)
                            .transform(RoundCornersTransform(16.0f))
                            .into(ivBookCover)

                        tvCoverMissing.visibility = View.GONE

                        viewModel.showLoadingCircle.postValue(false)
                    } else {
                        tvCoverMissing.visibility = View.VISIBLE
                        ivBookCover.setImageDrawable(holder.itemView.context.resources?.let {
                            ResourcesCompat.getDrawable(
                                it, R.drawable.ic_iconscout_book_24, null
                            )
                        })
                        val themeColor = getThemeAccentColor(holder.itemView.context)
                        val colorStateList = ColorStateList.valueOf(themeColor)
                        ivBookCover.setImageTintList(colorStateList)
                    }

                    if (curBook.data.isbn_13 != null) {
                        var isbn13: String? = curBook.data.isbn_13[0]
                        var isbn = "ISBN: $isbn13"
                        tvBookISBN.text = isbn
                    } else if (curBook.data.isbn_10 != null){
                        var isbn10: String? = curBook.data.isbn_10[0]
                        var isbn = "ISBN: $isbn10"
                        tvBookISBN.text = isbn
                    }

                    displayNumberOfPages(this, curBook)

                    displayPublishYear(this, curBook)

                    if (curBook.data.languages != null) {
                        var languagesList = curBook.data.languages
                        var languagesString = holder.itemView.context.getString(R.string.language_upperCase) + ": "

                        for (language in languagesList) {
                            languagesString += language.key.replace("/languages/", "")
                            languagesString += ", "
                        }

                        if (languagesString.last().toString() == " ")
                            languagesString = languagesString.dropLast(1)

                        if (languagesString.last().toString() == ",")
                            languagesString =languagesString.dropLast(1)

                        tvBookLanguage.text = languagesString
                    }
                }
            }
        }

        holder.itemView.apply {
            setOnClickListener {
                onBookClickListener?.let { it(curBook) }
            }
        }
    }

    private fun getThemeAccentColor(context: Context): Int {
        val value = TypedValue()
        context.theme.resolveAttribute(R.attr.colorAccent, value, true)
        return value.data
    }

    private fun displayPublishYear(view: View, curBook: Resource<OpenLibraryOLIDResponse>) {
        if (curBook.data?.publish_date != null) {
            var publishYear = curBook.data.publish_date

            if (publishYear != "0") {
                view.tvBookPublishYear.text = publishYear
                view.tvBookPublishYear.visibility = View.VISIBLE
            }
            else
                view.tvBookPublishYear.visibility = View.GONE
        }
    }

    private fun displayNumberOfPages(view: View, curBook: Resource<OpenLibraryOLIDResponse>) {
        if (curBook.data?.number_of_pages != null) {
            var pages: Int? = curBook.data.number_of_pages

            if (pages != null && pages > 0) {
                var pagesString = pages.toString() + " " + view.context.getString(R.string.pages)
                view.tvBookPages.text = pagesString
            } else
                view.tvBookPages.visibility = View.GONE
        }
    }

    fun setOnBookClickListener(listener: (Resource<OpenLibraryOLIDResponse>) -> Unit) {
        onBookClickListener = listener
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}