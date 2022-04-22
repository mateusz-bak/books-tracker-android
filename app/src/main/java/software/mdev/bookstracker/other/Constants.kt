package software.mdev.bookstracker.other

object Constants {

    const val BOOK_STATUS_READ = "read"
    const val BOOK_STATUS_IN_PROGRESS = "in_progress"
    const val BOOK_STATUS_TO_READ = "to_read"
    const val BOOK_STATUS_NOTHING = "nothing"

    const val DATABASE_NAME = "Book"
    const val DATABASE_FILE_NAME = "BooksDB.db"
    const val DATABASE_ITEM_BOOK_TITLE = "item_bookTitle"
    const val DATABASE_ITEM_BOOK_AUTHOR = "item_bookAuthor"
    const val DATABASE_ITEM_BOOK_RATING = "item_bookRating"
    const val DATABASE_ITEM_BOOK_STATUS = "item_bookStatus"
    const val DATABASE_ITEM_BOOK_PRIORITY = "item_bookPriority"
    const val DATABASE_ITEM_BOOK_START_DATE = "item_bookStartDate"
    const val DATABASE_ITEM_BOOK_FINISH_DATE = "item_bookFinishDate"
    const val DATABASE_ITEM_BOOK_NUMBER_OF_PAGES = "item_bookNumberOfPages"
    const val DATABASE_ITEM_BOOK_TITLE_ASCII = "item_bookTitle_ASCII"
    const val DATABASE_ITEM_BOOK_AUTHOR_ASCII = "item_bookAuthor_ASCII"
    const val DATABASE_ITEM_BOOK_COVER_URL = "item_bookCoverUrl"
    const val DATABASE_ITEM_BOOK_OLID = "item_bookOLID"
    const val DATABASE_ITEM_BOOK_ISBN10 = "item_bookISBN10"
    const val DATABASE_ITEM_BOOK_ISBN13 = "item_bookISBN13"
    const val DATABASE_ITEM_BOOK_PUBLISH_YEAR = "item_bookPublishYear"
    const val DATABASE_ITEM_BOOK_IS_DELETED = "item_bookIsDeleted"
    const val DATABASE_ITEM_BOOK_IS_FAV = "item_bookIsFav"
    const val DATABASE_ITEM_BOOK_COVER_IMG = "item_bookCoverImg"
    const val DATABASE_ITEM_BOOK_NOTES = "item_bookNotes"
    const val DATABASE_ITEM_BOOK_TAGS = "item_bookTags"
    const val DATABASE_EMPTY_VALUE = "none"

    const val DATABASE_NAME_YEAR = "Year"
    const val DATABASE_YEAR_FILE_NAME = "YearDB.db"
    const val DATABASE_YEAR_ITEM_YEAR = "item_year"
    const val DATABASE_YEAR_ITEM_BOOKS = "item_books"
    const val DATABASE_YEAR_ITEM_PAGES = "item_pages"
    const val DATABASE_YEAR_ITEM_RATING = "item_rating"
    const val DATABASE_YEAR_CHALLENGE_BOOKS = "item_challenge_books"
    const val DATABASE_YEAR_CHALLENGE_PAGES = "item_challenge_pages"
    const val DATABASE_YEAR_CHALLENGE_PAGES_CORRECTED = "item_challenge_pages_corrected"
    const val DATABASE_YEAR_QUICKEST_BOOK = "item_quickest_book"
    const val DATABASE_YEAR_QUICKEST_BOOK_ID = "item_quickest_book_id"
    const val DATABASE_YEAR_QUICKEST_BOOK_VAL = "item_quickest_book_val"
    const val DATABASE_YEAR_LONGEST_BOOK = "item_longest_book"
    const val DATABASE_YEAR_LONGEST_BOOK_ID = "item_longest_book_id"
    const val DATABASE_YEAR_LONGEST_BOOK_VAL = "item_longest_book_val"
    const val DATABASE_YEAR_AVG_READING_TIME = "item_avg_reading_time"
    const val DATABASE_YEAR_AVG_PAGES = "item_avg_pages"
    const val DATABASE_YEAR_SHORTEST_BOOK = "item_shortest_book"
    const val DATABASE_YEAR_SHORTEST_BOOK_ID = "item_shortest_book_id"
    const val DATABASE_YEAR_SHORTEST_BOOK_VAL = "item_shortest_book_val"
    const val DATABASE_YEAR_BOOKS_BY_MONTH = "item_books_by_month"
    const val DATABASE_YEAR_PAGES_BY_MONTH = "item_pages_by_month"
    const val DATABASE_YEAR_READ_BOOKS = "item_read_books"
    const val DATABASE_YEAR_IN_PROGRESS_BOOKS = "item_in_progress_books"
    const val DATABASE_YEAR_TO_READ_BOOKS = "item_to_read_books"
    const val DATABASE_YEAR_LONGEST_READ_BOOK = "item_longest_read_book"
    const val DATABASE_YEAR_LONGEST_READ_BOOK_ID = "item_longest_read_book_id"
    const val DATABASE_YEAR_LONGEST_READ_VAL = "item_longest_read_val"

    const val DATABASE_NAME_LANGUAGE = "Language"
    const val DATABASE_LANGUAGE_FILE_NAME = "LanguageDB.db"
    const val DATABASE_LANGUAGE_ITEM_language6392B = "item_language6392B"
    const val DATABASE_LANGUAGE_ITEM_isoLanguageName = "item_isoLanguageName"
    const val DATABASE_LANGUAGE_ITEM_isSelected = "item_isSelected"
    const val DATABASE_LANGUAGE_ITEM_selectCounter = "item_selectCounter"
    const val DATABASE_LANGUAGE_ITEM_isoLanguageName_pol = "item_isoLanguageName_pol"

    const val CHALLENGE_BEGINNER = 3
    const val CHALLENGE_EASY = 6
    const val CHALLENGE_NORMAL = 12
    const val CHALLENGE_HARD = 18
    const val CHALLENGE_INSANE = 24


    const val SORT_ORDER_TITLE_DESC = "ivSortTitleDesc"
    const val SORT_ORDER_TITLE_ASC = "ivSortTitleAsc"
    const val SORT_ORDER_AUTHOR_DESC = "ivSortAuthorDesc"
    const val SORT_ORDER_AUTHOR_ASC = "ivSortAuthorAsc"
    const val SORT_ORDER_RATING_DESC = "ivSortRatingDesc"
    const val SORT_ORDER_RATING_ASC = "ivSortRatingAsc"
    const val SORT_ORDER_PAGES_DESC = "ivSortPagesDesc"
    const val SORT_ORDER_PAGES_ASC = "ivSortPagesAsc"
    const val SORT_ORDER_START_DATE_DESC = "ivSortStartDateDesc"
    const val SORT_ORDER_START_DATE_ASC = "ivSortStartDateAsc"
    const val SORT_ORDER_FINISH_DATE_DESC = "ivSortFinishDateDesc"
    const val SORT_ORDER_FINISH_DATE_ASC = "ivSortFinishDateAsc"

    const val SHARED_PREFERENCES_KEY_FIRST_TIME_TOGGLE = "KEY_FIRST_TIME_TOGGLE"
    const val SHARED_PREFERENCES_KEY_SHOW_TAGS = "KEY_SHOW_TAGS"
    const val SHARED_PREFERENCES_KEY_APP_VERSION = "SHARED_PREFERENCES_KEY_APP_VERSION"
    const val SHARED_PREFERENCES_KEY_SORT_ORDER = "KEY_SORT_ORDER"
    const val SHARED_PREFERENCES_KEY_FILTER_YEARS = "KEY_FILTER_YEARS"
    const val SHARED_PREFERENCES_KEY_ONLY_FAV = "KEY_ONLY_FAV"
    const val SHARED_PREFERENCES_KEY_FILTER_TAGS = "KEY_FILTER_TAGS"
    const val SHARED_PREFERENCES_KEY_ACCENT = "KEY_ACCENT"
    const val SHARED_PREFERENCES_KEY_THEME_MODE = "KEY_THEME_MODE"
    const val SHARED_PREFERENCES_KEY_LANDING_PAGE = "KEY_LANDING_PAGE"
    const val SHARED_PREFERENCES_KEY_TIME_TO_ASK_FOR_RATING = "KEY_TIME_TO_ASK_FOR_RATING"
    const val SHARED_PREFERENCES_KEY_RECOMMENDATIONS = "KEY_RECOMMENDATIONS"
    const val SHARED_PREFERENCES_KEY_SHOW_OL_ALERT = "KEY_SHOW_OL_ALERT"
    const val SHARED_PREFERENCES_KEY_DETAILS_MODE = "KEY_SHOW_OL_ALERT"
    const val SHARED_PREFERENCES_REFRESHED = "refreshed"
    const val KEY_CHECK_FOR_UPDATES = "KEY_CHECK_FOR_UPDATES"
    const val KEY_TRASH = "KEY_TRASH"
    const val KEY_BACKUP = "KEY_BACKUP"
    const val KEY_EXPORT = "KEY_EXPORT"
    const val KEY_EXPORT_LOCAL = "KEY_EXPORT_LOCAL"
    const val KEY_IMPORT = "KEY_IMPORT"
    const val KEY_CHANGELOG = "KEY_CHANGELOG"
    const val KEY_FEEDBACK = "KEY_FEEDBACK"

    const val EMPTY_STRING = ""

    const val THEME_ACCENT_LIGHT_GREEN = "accent_light_green"
    const val THEME_ACCENT_ORANGE_500 = "accent_orange"
    const val THEME_ACCENT_CYAN_500 = "accent_cyan"
    const val THEME_ACCENT_GREEN_500 = "accent_green"
    const val THEME_ACCENT_BROWN_400 = "accent_brown"
    const val THEME_ACCENT_LIME_500 = "accent_lime"
    const val THEME_ACCENT_PINK_300 = "accent_pink"
    const val THEME_ACCENT_PURPLE_500 = "accent_purple"
    const val THEME_ACCENT_TEAL_500 = "accent_teal"
    const val THEME_ACCENT_YELLOW_500 = "accent_yellow"

    const val THEME_MODE_AUTO = "theme_mode_auto"
    const val THEME_MODE_DAY = "theme_mode_day"
    const val THEME_MODE_NIGHT = "theme_mode_night"

    const val KEY_LANDING_PAGE_FINISHED = "book_list_finished"
    const val KEY_LANDING_PAGE_IN_PROGRESS = "book_list_inProgress"
    const val KEY_LANDING_PAGE_TO_READ = "book_list_toRead"

    const val THEME_ACCENT_DEFAULT = THEME_ACCENT_GREEN_500

    const val GITHUB_USER = "mateusz-bak"
    const val GITHUB_REPO = "openreads-android"

    const val BASE_URL = "https://openlibrary.org/"
    const val OPEN_LIBRARY_SEARCH_DELAY = 500L

    // permission request codes
    const val PERMISSION_CAMERA_FROM_BOOK_LIST = 1
    const val PERMISSION_CAMERA_FROM_UPLOAD_COVER = 4
    const val PERMISSION_READ_EXTERNAL_STORAGE_FROM_UPLOAD_COVER = 5

    const val MS_ONE_WEEK = 604800000L
    const val MS_THREE_DAYS = 259200000L

    // arguments between fragments
    const val SERIALIZABLE_BUNDLE_BOOK = "book"
    const val SERIALIZABLE_BUNDLE_BOOK_STATUS = "bookStatus"
    const val SERIALIZABLE_BUNDLE_ISBN = "isbn"
    const val SERIALIZABLE_BUNDLE_ISBN_DEFAULT = "manual_search"
    const val SERIALIZABLE_BUNDLE_BOOK_SOURCE = "bookSource"
    const val SERIALIZABLE_BUNDLE_ACCENT = "accent"

    // sources of book when passing to add/edit fragment
    const val NO_SOURCE = 0
    const val FROM_SEARCH = 1
    const val FROM_SCAN = 2
    const val FROM_DISPLAY = 3

    // books details
    const val detailStartDate = 0
    const val detailFinishDate = 1
    const val detailReadingTime = 2
    const val detailPages = 3
    const val detailPublishYear = 4
    const val detailTags = 5
    const val detailISBN = 6
    const val detailOLID = 7
    const val detailNotes = 8
    const val detailUrl = 9
}