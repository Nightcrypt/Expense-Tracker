package cm.lobe.loic.expensetracker.di

import cm.lobe.loic.expensetracker.BuildConfig
import cm.lobe.loic.expensetracker.data.DefaultTransactionRepository
import cm.lobe.loic.expensetracker.data.FirestoreTransactionDataSource
import cm.lobe.loic.expensetracker.data.TransactionDataSource
import cm.lobe.loic.expensetracker.data.TransactionRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.firestoreSettings
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.ktx.functions
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class SharedModule {

//    TransactionDataSource

    // Repository Providers
    @Singleton
    @Provides
    fun provideTransactionRepository(
        TransactionDataSource: TransactionDataSource
    ): TransactionRepository {
        return DefaultTransactionRepository(TransactionDataSource)
    }

    // DataSource Providers
    @Singleton
    @Provides
    fun provideTransactionDataSource(firestore: FirebaseFirestore): TransactionDataSource {
        return FirestoreTransactionDataSource(firestore)
    }

    // Firebase Providers
    @Singleton
    @Provides
    fun provideFirebaseFireStore(): FirebaseFirestore {
        return Firebase.firestore.apply {
            // This is to enable the offline data
            // https://firebase.google.com/docs/firestore/manage-data/enable-offline
            firestoreSettings = firestoreSettings { isPersistenceEnabled = true }
        }
    }

    @Singleton
    @Provides
    fun provideFirebaseFunctions(): FirebaseFunctions {
        return Firebase.functions
    }

    @Singleton
    @Provides
    fun provideFirebaseRemoteConfigSettings(): FirebaseRemoteConfigSettings {
        return if (BuildConfig.DEBUG) {
            remoteConfigSettings { minimumFetchIntervalInSeconds = 0 }
        } else {
            remoteConfigSettings { }
        }
    }

    @Singleton
    @Provides
    fun provideFirebaseStorage(): FirebaseStorage {
        return Firebase.storage
    }

    @Singleton
    @Provides
    fun provideFirebaseRemoteConfig(
        configSettings: FirebaseRemoteConfigSettings
    ): FirebaseRemoteConfig {
        return Firebase.remoteConfig.apply {
            setConfigSettingsAsync(configSettings)
            //setDefaultsAsync(R.xml.remote_config_defaults)
        }
    }
}