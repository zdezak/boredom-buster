import eu.maxkim.boredombuster.activity.model.Activity
import eu.maxkim.boredombuster.activity.usecase.GetRandomActivity
import eu.maxkim.boredombuster.model.Result

class FakeGetRandomActivity(private val isSuccessful: Boolean = true) : GetRandomActivity {
    //var activity: Activity? = null
    var activity = activity1
    override suspend fun invoke(): Result<Activity> {
        return if (isSuccessful) {
            //Result.Successful(activity?: activity1)
            Result.Success(activity)
        } else {
            Result.Error(RuntimeException("Boom..."))
        }
    }
}
