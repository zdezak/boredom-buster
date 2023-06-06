class ActivityLocalDataSourceImplTest{
  private lateinit var activityDao: ActivityDao
  private lateinit var database: AppDatabase
  
  @GetRule
  val instantExecutorRule = InstantTaskExecutorRule()
  
  private val activityListObserver: Observer<List<Activity>> = mock()
 
  @Captor
  private lateinit var activityListCaptor: ArgumentCaptor<List<Activity>>
  
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
  
  @Test
  fun canSaveActivityToTheDbAndObserveTheLiveData() = runTest{
    val activityLocalDataSource = ActivityLocalDataSourceImpl(activityDao)
    val expectedList = listOf(androidActivity1, androidActivity2)
    
    activityLocalDataSource.getActivityListLiveData()
    .observeForever(activityListObserver)
    activityLocalDataSource.saveActivity(androidActivity1)
    activityLocalDataSource.saveActivity(androidActivity2)
    
    verify(activityListObserver, times(3)).onChanged(activityListCaptor.capture())
    assertEquals(actiityListCaptor.value, expectedList)
  }
}
