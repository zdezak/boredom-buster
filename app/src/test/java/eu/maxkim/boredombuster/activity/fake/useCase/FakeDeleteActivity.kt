import eu.maxkim.boredombuster.activity.model.Activity
import eu.maxkim.boredombuster.activity.usecase.DeleteActivity

class FakeDeleteActivity : DeleteActivity {
    override suspend fun invoke(activity: Activity) {
        TODO("Not yet implemented")
    }
}
