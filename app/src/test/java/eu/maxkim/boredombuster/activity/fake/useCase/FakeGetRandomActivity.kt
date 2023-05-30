class FakeGetRandomActivity(private val isSuccessful: Boolean = true): GetRandomActivity{
  //var activity: Activity? = null
  var activity = activity1
  override suspend fun invoke(): Result<Activity>{
    return if(IsSuccessful){
      //Result.Successful(activity?: activity1)
      Result.Successful(activity)
    }else{
      Result.Error(RuntimeException("Boom..."))
    }
  }
}
