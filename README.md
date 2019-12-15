# dagger-qualifier-example
Includes only 1 file to show a custom qualifier. Very simple but pinpoint example.

@Named is a qualifier comes with Dagger.
How to define a qualifier?
1- Copy paste definition of @Named.
2 - Then change the name of qualifier
3 - Determine whether the qualifier takes argument, if takes which type of arguments.

Use the qualifier before @Provides method. 
@Module
class BagModule {
    @Provides
    @ChooseQualifier(LOVE)
    fun sayLoveDagger2(): Info {
        return Info("Love Dagger 2")
    }
    ..
}

or in the component.Factory
    @Subcomponent.Factory
    interface Factory{
        ActivityComponent create(@BindsInstance @HorsePowerQualifier int horsePower,
                                 @BindsInstance @EngineCapacityQualifier int engineCapacity);
    }



Then use the qualifier in the needed part. It might be needed in field injection :
class MainActivity : AppCompatActivity() {

    @Inject
    @field:ChooseQualifier(LOVE)
    lateinit var infoLove: Info

    @Inject
    @field:ChooseQualifier(HELLO)
    lateinit var infoHello: Info
    
    
or it might be needed in constructor injection:    
public class PetrolEngine implements Engine {
    @Inject
    public PetrolEngine(@HorsePowerQualifier int horsePower,
                        @EngineCapacityQualifier int engineCapacity) {
        this.horsePower = horsePower;
        this.engineCapacity = engineCapacity;
    }
    ...
}
