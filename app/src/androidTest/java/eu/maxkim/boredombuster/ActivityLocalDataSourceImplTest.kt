class ActivityLocalDataSourceImplTest{
  private lateinit var activityDao: ActivityDao
  private lateinit var database: AppDatabase
  
  @Before
  fun createDb(){
    val context = ApplicationProvider.getApplicationContext<Context>()
    database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
    .build()
    activityDao = database.activityDao()
  }
  
  @After
  fun closeDb(){
    database.close()
  }
  
  @Test
  fun canSaveActivityToTheDbAndReadIt() = runTest{
    val activityLocalDataSourse = ActivityLocalDataSourceImpl(activityDao)
    
    activityLocalDataSource.saveActivity(androidActivity1)
    
    assert(activityLocalDataSource.isActivitySaved(androidActivity1.key))
  }
  
  @Test
  fun canDeleteActivityFromTheDb() = runTest{
    val activityLoacalDataSource = ActivityLocalDataSourceImpl(activityDao)
    activityLocalDataSource.saveActivity(androidActivity1)
    
    activityLocalDataSource.deleteActivity(androidActivity1)
    
    assert(!activityLocalDataSource.isActivitySaved(androidActivity1.key))
  }
}
