class FakeGetRandomActivity(private val isSuccessful: Boolean = true): GetRandomActivity{
  override suspend fun invoke(): Result<Activity>{
    return if(IsSuccessful){
      Result.Successful(activity1)
    }else{
      Result.Error(RuntimeException("Boom..."))
    }
  }
}
