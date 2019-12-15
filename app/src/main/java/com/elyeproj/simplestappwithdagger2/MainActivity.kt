package com.elyeproj.simplestappwithdagger2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import javax.inject.Qualifier


const val LOVE = "Love"
const val HELLO = "Hello"

class MainActivity : AppCompatActivity() {

    @Inject
    @field:ChooseQualifier(LOVE)
    lateinit var infoLove: Info

    @Inject
    @field:ChooseQualifier(HELLO)
    lateinit var infoHello: Info

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerMagicBoxComponent.create().inject(this)
        text_view.text = "${infoLove.text} ${infoHello.text}"
    }
}

class Info(val text: String)

@Module
class BagModule {
    @Provides
    @ChooseQualifier(LOVE)
    fun sayLoveDagger2(): Info {
        return Info("Love Dagger 2")
    }

    @Provides
    @ChooseQualifier(HELLO)
    fun sayHelloDagger2(): Info {
        return Info("Hello Dagger 2")
    }
}

@Component(modules = [BagModule::class])
interface MagicBoxComponent {
    fun inject(app: MainActivity)
}

@Qualifier
@MustBeDocumented
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class ChooseQualifier(val value: String = "")
