package pl.mdanilowski.myapplication

import android.content.Intent
import androidx.test.runner.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith
import pl.mdanilowski.myapplication.ui.dashboard.DashboardActivity

@RunWith(AndroidJUnit4::class)
class DashboardActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<DashboardActivity> = ActivityTestRule(
        DashboardActivity::class.java)

    @Test
    fun testEvent() {
        val scenario = activityRule.launchActivity(Intent())
        scenario.recreate()
    }
}
