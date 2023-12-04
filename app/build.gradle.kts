plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.gms.google-services")
}

android {
    namespace = "br.senai.sp.jandira.ayancare_frontmobile_cuidador"
    compileSdk = 34

    defaultConfig {
        applicationId = "br.senai.sp.jandira.ayancare_frontmobile_cuidador"
        minSdk = 27
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    kotlin{
        jvmToolchain(18)
    }
}



tasks.withType(type = com.google.devtools.ksp.gradle.KspTaskJvm::class) {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-graphics-android:1.5.1")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("com.google.firebase:firebase-storage:20.3.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //Mais Icones
    implementation ("androidx.compose.material:material-icons-extended:1.2.0")

    //Implementacao do bottom navigation
    implementation ("androidx.compose.material:material:1.4.2")

    //Dependencias do navController
    implementation("androidx.navigation:navigation-compose:2.6.0")
    implementation("com.google.accompanist:accompanist-navigation-animation:0.30.1")

    //Dependecias Retrofit
    implementation ("com.google.code.gson:gson:2.8.9")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.0")

    // implementação de data no textfield
    implementation("androidx.compose.material3:material3:1.2.0-alpha02")
    implementation("com.google.android.material:material:1.6.0-alpha01")

    //Dependência para a biblioteca do Jetpack Compose
    implementation("androidx.compose.ui:ui:1.1.0")

    //Dependência para componentes de IU do Jetpack Compose
    implementation("androidx.compose.foundation:foundation:1.1.0")
    implementation("androidx.compose.runtime:runtime:1.1.0")

    //Dependência para componentes de IU do Material Design no Jetpack Compose
    implementation("androidx.compose.material:material:1.1.0")

    //Dependência para componentes de IU do Jetpack Navigation Compose
    implementation("androidx.navigation:navigation-compose:2.4.0")

    //Dependência para ViewModel no Jetpack Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07")

    //Dependência para Hilt (para injeção de dependência no Jetpack Compose)
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0-alpha03")

    //Modal
    implementation ("androidx.compose.material3:material3:1.0.0-alpha01")

    //CORE
    implementation ("androidx.core:core-ktx:1.7.0")

    /// ROOM DEPENDENCIES (SQLite)
    implementation ("androidx.room:room-runtime:2.5.2")
    annotationProcessor ("androidx.room:room-compiler:2.5.2")
    ksp("androidx.room:room-compiler:2.5.2")

    //COIL
    implementation("io.coil-kt:coil-compose:2.3.0")
    implementation("io.coil-kt:coil:2.3.0")

    //Lottie
    implementation("com.airbnb.android:lottie-compose:6.0.0")

    //permissões
    implementation("com.google.accompanist:accompanist-permissions:0.30.1")

}