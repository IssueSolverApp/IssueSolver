import com.issuesolver.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val iconId: Int
) {
    object Home : BottomBarScreen(
        route = "HOME",
        title = "Home",
        iconId = R.drawable.home_ic
    )

    object MyRequest : BottomBarScreen(
        route = "MY_REQUESTS",
        title = "My Requests",
        iconId = R.drawable.groupbottombar
    )

    object NewRequest : BottomBarScreen(
        route = "NEW_REQUEST",
        title = "New Request",
        iconId = R.drawable.ph_plus
    )

    object Profile : BottomBarScreen(
        route = "PROFILE",
        title = "Profile",
        iconId = R.drawable.profile
    )
}
