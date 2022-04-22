package software.mdev.bookstracker.other

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Resources
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import software.mdev.bookstracker.R
import software.mdev.bookstracker.adapters.BookAdapter
import software.mdev.bookstracker.api.RetrofitInstance.Companion.gson
import software.mdev.bookstracker.data.db.entities.Book
import software.mdev.bookstracker.ui.bookslist.ListActivity
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class Functions {

    fun convertLongToYear(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("yyyy")
        return format.format(date)
    }

    fun convertYearToLong(year: String): Long {
        val timeStamp = SimpleDateFormat("yyyy").parse(year)
        return timeStamp.time
    }

    fun convertLongToMonth(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("MM")
        return format.format(date)
    }

    fun convertLongToDays(time: Long, resources: Resources): String {
        val days = TimeUnit.MILLISECONDS.toDays(time)

        return if (days == 1L)
            "$days ${resources.getString(R.string.day)}"
        else
            "$days ${resources.getString(R.string.days)}"
    }

    fun calculateYearsFromDb(listOfBooks: List<Book>): Array<String> {
        var arrayOfYears: Array<String>

        var years = listOf<String>()
        var year: Int

        for (item in listOfBooks) {
            if (item.bookFinishDate != "null" && item.bookFinishDate != "none") {
                year = convertLongToYear(item.bookFinishDate.toLong()).toInt()
                if (year.toString() !in years) {
                    years = years + year.toString()
                }
            }
        }

        arrayOfYears = years.toTypedArray()
        return arrayOfYears
    }


    @SuppressLint("NotifyDataSetChanged")
    fun filterBooksList(
        activity: ListActivity,
        bookAdapter: BookAdapter,
        notFilteredBooks: List<Book>,
        rv: RecyclerView?,
        scrollToTop: Boolean
    ) {
        var sharedPreferencesName = activity.getString(R.string.shared_preferences_name)
        val sharedPref = (activity).getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE)

        var filteredBooks1: List<Book> = emptyList()

        val yearsToFilterJson = sharedPref.getString(Constants.SHARED_PREFERENCES_KEY_FILTER_YEARS, "null")

        if (yearsToFilterJson != null && yearsToFilterJson != "null") {
            val yearsToFilter = gson.fromJson(yearsToFilterJson, Array<String>::class.java).toList()
            for (book in notFilteredBooks) {
                for (yearToBeShown in yearsToFilter) {
                    val startTimeStamp = convertYearToLong(yearToBeShown)
                    val endTimeStamp = convertYearToLong((yearToBeShown.toInt() + 1).toString())

                    if (book.bookStatus == Constants.BOOK_STATUS_IN_PROGRESS ||
                        book.bookStatus == Constants.BOOK_STATUS_TO_READ) {
                        if (book !in filteredBooks1)
                            filteredBooks1 += book
                    } else if ((book.bookFinishDate != "null" && book.bookFinishDate != "none")) {
                        if (book.bookFinishDate.toLong() in startTimeStamp..endTimeStamp) {
                            if (book !in filteredBooks1)
                                filteredBooks1 += book
                        }
                    }
                }
            }
        } else
            filteredBooks1 = notFilteredBooks

        var filteredBooks2: List<Book> = emptyList()
        val tagsToFilterJson = sharedPref.getString(Constants.SHARED_PREFERENCES_KEY_FILTER_TAGS, "null")

        if (tagsToFilterJson != null && tagsToFilterJson != "null") {
            val tagsToFilter = gson.fromJson(tagsToFilterJson, Array<String>::class.java).toList()

            for (book in filteredBooks1) {
                if (book.bookTags != null && book.bookTags!!.isNotEmpty()) {
                    for (tag in book.bookTags!!) {
                        if (tag in tagsToFilter) {
                            if (book !in filteredBooks2)
                                filteredBooks2 += book
                        }
                    }
                }
            }
        } else
            filteredBooks2 = filteredBooks1

        bookAdapter.differ.submitList(filteredBooks2)
        // required to reload RecyclerView in order to correctly display pages or dates according
        // to sort type
        bookAdapter.notifyDataSetChanged()

        if (scrollToTop && rv != null) {
            GlobalScope.launch {
                delay(250L)
                rv.smoothScrollToPosition(0)

            }
        }
    }

    fun getAccentColor(context: Context): Int {

        var accentColor = ContextCompat.getColor(context, R.color.purple_500)

        var sharedPreferencesName = context.getString(R.string.shared_preferences_name)
        val sharedPref = context.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE)

        var accent = sharedPref?.getString(
            Constants.SHARED_PREFERENCES_KEY_ACCENT,
            Constants.THEME_ACCENT_DEFAULT
        ).toString()

        when(accent){
            Constants.THEME_ACCENT_LIGHT_GREEN -> accentColor = ContextCompat.getColor(context, R.color.light_green)
            Constants.THEME_ACCENT_ORANGE_500 -> accentColor = ContextCompat.getColor(context, R.color.orange_500)
            Constants.THEME_ACCENT_CYAN_500 -> accentColor = ContextCompat.getColor(context, R.color.cyan_500)
            Constants.THEME_ACCENT_GREEN_500 -> accentColor = ContextCompat.getColor(context, R.color.green_500)
            Constants.THEME_ACCENT_BROWN_400 -> accentColor = ContextCompat.getColor(context, R.color.brown_400)
            Constants.THEME_ACCENT_LIME_500 -> accentColor = ContextCompat.getColor(context, R.color.lime_500)
            Constants.THEME_ACCENT_PINK_300 -> accentColor = ContextCompat.getColor(context, R.color.pink_300)
            Constants.THEME_ACCENT_PURPLE_500 -> accentColor = ContextCompat.getColor(context, R.color.purple_500)
            Constants.THEME_ACCENT_TEAL_500 -> accentColor = ContextCompat.getColor(context, R.color.teal_500)
            Constants.THEME_ACCENT_YELLOW_500 -> accentColor = ContextCompat.getColor(context, R.color.yellow_500)
        }
        return accentColor
    }

    fun checkPermission(activity: ListActivity, permission: String) =
        ActivityCompat.checkSelfPermission(activity.baseContext, permission) == PackageManager.PERMISSION_GRANTED

    fun requestPermission(activity: ListActivity, permission: String, requestCode: Int) {
        ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode)
    }
}