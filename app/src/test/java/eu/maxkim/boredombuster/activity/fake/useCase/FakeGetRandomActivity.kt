class FakeGetRandomActivity(private val isSuccessful: Boolean = true): GetRandomActivity{
  override suspend fun invoke(): Result<Activity>{
    TODO("Not yet implemented")
  }
}
