//package com.issuesolver.presentation.navigation
//
//import androidx.compose.animation.EnterTransition
//import androidx.compose.animation.ExitTransition
//import androidx.compose.animation.core.tween
//import androidx.compose.animation.fadeIn
//import androidx.compose.animation.fadeOut
//import androidx.compose.animation.scaleIn
//import androidx.compose.animation.scaleOut
//import androidx.compose.runtime.Composable
//import androidx.navigation.NavType
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.navigation
//import androidx.navigation.compose.rememberNavController
//import androidx.navigation.navArgument
//import com.issuesolver.presentation.login.daxil_ol_page.LoginPage
//import com.issuesolver.presentation.login.daxil_ol_page_email.EmailVerificationPage
//import com.issuesolver.presentation.login.daxil_ol_verification_code.VerificationCodePage
//import com.issuesolver.presentation.login.password_change_page.PasswordChangePage
//import com.issuesolver.presentation.login.qeydiyyat_page.RegisterOtpCodePage
//import com.issuesolver.presentation.login.qeydiyyat_page.RegisterPage
//import com.issuesolver.presentation.profile.enter_password.DeleteAccountScreen
//import com.issuesolver.presentation.profile.my_account.MyAccountScreen
//import com.issuesolver.presentation.profile.new_password.NewPasswordScreen
//import com.issuesolver.presentation.profile.profile.ProfileScreen
//
//@Composable
//fun AppNavigation() {
//    val navController = rememberNavController()
//    NavHost(navController = navController, startDestination = "auth_graph") {
//
//        navigation(startDestination = Routes.LOGIN, route = "auth_graph"){
//            composable(Routes.LOGIN, enterTransition = {
//                scaleIntoContainer()
//
//            },
//                exitTransition = {
//                    scaleOutOfContainer(direction = ScaleTransitionDirection.INWARDS)
//                },
//                popEnterTransition = {
//                    scaleIntoContainer(direction = ScaleTransitionDirection.OUTWARDS)
//                },
//                popExitTransition = {
//                    scaleOutOfContainer()
//                }) { LoginPage(navController) }
//            composable(Routes.EMAIL_VERIFICATION, enterTransition = {
//                scaleIntoContainer()
//            },
//                exitTransition = {
//                    scaleOutOfContainer(direction = ScaleTransitionDirection.INWARDS)
//                },
//                popEnterTransition = {
//                    scaleIntoContainer(direction = ScaleTransitionDirection.OUTWARDS)
//                },
//                popExitTransition = {
//                    scaleOutOfContainer()
//                }) { EmailVerificationPage(navController) }
//            composable(Routes.OTP, enterTransition = {
//                scaleIntoContainer()
//            },
//                exitTransition = {
//                    scaleOutOfContainer(direction = ScaleTransitionDirection.INWARDS)
//                },
//                popEnterTransition = {
//                    scaleIntoContainer(direction = ScaleTransitionDirection.OUTWARDS)
//                },
//                popExitTransition = {
//                    scaleOutOfContainer()
//                }) { VerificationCodePage(navController) }
//            composable(Routes.PASSWORD_CHANGE, enterTransition = {
//                scaleIntoContainer()
//            },
//                exitTransition = {
//                    scaleOutOfContainer(direction = ScaleTransitionDirection.INWARDS)
//                },
//                popEnterTransition = {
//                    scaleIntoContainer(direction = ScaleTransitionDirection.OUTWARDS)
//                },
//                popExitTransition = {
//                    scaleOutOfContainer()
//                }) { PasswordChangePage(navController) }
//            composable(Routes.REGISTER, enterTransition = {
//                scaleIntoContainerContainer()
//            },
//                exitTransition = {
//                    scaleOutOfContainer(direction = ScaleTransitionDirection.INWARDS)
//                },
//                popEnterTransition = {
//                    scaleIntoContainer(direction = ScaleTransitionDirection.OUTWARDS)
//                },
//                popExitTransition = {
//                    scaleOutOfContainer()
//                }) { RegisterPage(navController) }
//            composable(Routes.REGISTER_OTP + "/{id}", enterTransition = {
//                scaleIntoContainer()
//            },
//                exitTransition = {
//                    scaleOutOfContainer(direction = ScaleTransitionDirection.INWARDS)
//                },
//                popEnterTransition = {
//                    scaleIntoContainer(direction = ScaleTransitionDirection.OUTWARDS)
//                },
//                popExitTransition = {
//                    scaleOutOfContainer()
//                }) { navBackStack ->
//                val email = navBackStack.arguments?.getString("id")
//                RegisterOtpCodePage(navController = navController, email = email)
//            }
//
//
//        }
//        //
//
//        navigation(startDestination = Routes.PROFILE_PAGE, route = "profile_graph") {
//            composable(Routes.PROFILE_PAGE, enterTransition = {
//                scaleIntoContainer()
//
//            },
//                exitTransition = {
//                    scaleOutOfContainer(direction = ScaleTransitionDirection.INWARDS)
//                },
//                popEnterTransition = {
//                    scaleIntoContainer(direction = ScaleTransitionDirection.OUTWARDS)
//                },
//                popExitTransition = {
//                    scaleOutOfContainer()
//                }) { ProfileScreen(navController) }
//            composable(Routes.PROFILE_NEW_PASSWORD, enterTransition = {
//                scaleIntoContainer()
//
//            },
//                exitTransition = {
//                    scaleOutOfContainer(direction = ScaleTransitionDirection.INWARDS)
//                },
//                popEnterTransition = {
//                    scaleIntoContainer(direction = ScaleTransitionDirection.OUTWARDS)
//                },
//                popExitTransition = {
//                    scaleOutOfContainer()
//                }) { NewPasswordScreen(navController) }
//            composable(Routes.PROFILE_MY_ACCOUNT) { MyAccountScreen(navController) }
//
//            composable(Routes.PROFILE_DELETE_ACCOUNT, enterTransition = {
//                scaleIntoContainer()
//
//            },
//                exitTransition = {
//                    scaleOutOfContainer(direction = ScaleTransitionDirection.INWARDS)
//                },
//                popEnterTransition = {
//                    scaleIntoContainer(direction = ScaleTransitionDirection.OUTWARDS)
//                },
//                popExitTransition = {
//                    scaleOutOfContainer()
//                }) {
//                DeleteAccountScreen(navController)
//            }
//
//        }
//    }
//}
//
//fun scaleIntoContainer(
//    direction: ScaleTransitionDirection = ScaleTransitionDirection.INWARDS,
//    initialScale: Float = if (direction == ScaleTransitionDirection.OUTWARDS) 0.9f else 1.1f
//): EnterTransition {
//    return scaleIn(
//        animationSpec = tween(220, delayMillis = 90),
//        initialScale = initialScale
//    ) + fadeIn(animationSpec = tween(220, delayMillis = 90))
//}
//
//fun scaleOutOfContainer(
//    direction: ScaleTransitionDirection = ScaleTransitionDirection.OUTWARDS,
//    targetScale: Float = if (direction == ScaleTransitionDirection.INWARDS) 0.9f else 1.1f
//): ExitTransition {
//    return scaleOut(
//        animationSpec = tween(
//            durationMillis = 220,
//            delayMillis = 90
//        ), targetScale = targetScale
//    ) + fadeOut(tween(delayMillis = 90))
//}