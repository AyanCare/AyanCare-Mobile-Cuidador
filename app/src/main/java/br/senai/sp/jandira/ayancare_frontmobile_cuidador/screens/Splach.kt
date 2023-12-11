package br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.settings.screen.contasVinculadas.screen.LinkedAccountsScreen
import br.senai.sp.jandira.ayancare_frontmobile.screens.settings.screen.notificacoes.screen.NotificationScreen
import br.senai.sp.jandira.ayancare_frontmobile.screens.settings.screen.sugestao.SuggestionScreen
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.cadastro.screen.CadastroScreen
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.finalizarCadastro.screen.AddExperienceScreen
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.finalizarCadastro.screen.FinalizarCadastroScreen
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.login.screen.LoginScreen
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.menuBar.MainScreen
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.patient.screen.CalendaryScreen
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.patient.screen.CalendaryScreen.Screen.EventScreen.Screen.EventScreen
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.perfil.screen.editProfile.screen.EditProfileScreen
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.relatorio.screen.RelatoriosScreen
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.relatorio.screen.addRelatorio.screen.AddRelatorioScreen
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.relatorio.screen.questionario.screen.AddQuestionScreen
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.relatorio.screen.questionario.screen.QuestionScreen
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.relatorio.screen.view.RelatorioByIdPacienteScreen
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.relatorio.screen.view.RelatorioScreen
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.relatorio.screen.view.RelatoriosByIdPacienteScreen
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.relatorio.screen.view.ViewQuestionScreen
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.relatorioHumor.screen.RelatorioHumorScreen
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.relatorioHumor.screen.RelatoriosHumorScreen
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.settings.screen.SettingsScreen
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.settings.screen.codigoPaciente.screen.PatientCodeScreen
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.settings.screen.contasVinculadas.screen.profileCaregiver.screen.ProfileCaregiverScreen
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.telaPrincipal.screen.TelaPrincipalScreen
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.telasInstrucoes.telaInstrucao1.screen.TelaInstrucao1Screen
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.telasInstrucoes.telaInstrucao2.screen.TelaInstrucao2Screen
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.screens.telasInstrucoes.telaInstrucao3.screen.TelaInstrucao3Screen
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.sqlite.repository.CuidadorRepository
import br.senai.sp.jandira.ayancare_frontmobile_cuidador.ui.theme.AyanCareCuidadorTheme

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AyanCareCuidadorTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    val context = LocalContext.current
                    val localStorage: Storage = Storage()

                    NavHost(
                        navController = navController,
                        //startDestination = "main_screen"
                        startDestination =
                            if (CuidadorRepository(context).findUsers().isEmpty()){
                                "tela_principal_screen"
                            }else{
                                "main_screen"
                            }

                    ) {
                        composable("tela_principal_screen") {
                            TelaPrincipalScreen(navController = navController)
                        }

                        composable("login_screen") {
                            LoginScreen(navController = navController, lifecycleScope = lifecycleScope)
                        }

                        composable("cadastro_screen") {
                            CadastroScreen(navController = navController, lifecycleScope = lifecycleScope, navRotasController = navController, localStorage = localStorage)
                        }

                        composable("finalizar_cadastro_screen"){
                            FinalizarCadastroScreen(navController = navController, lifecycleScope = lifecycleScope, localStorage = localStorage)
                        }
                        composable("add_experience_screen"){
                            AddExperienceScreen(navController = navController, lifecycleScope = lifecycleScope, localStorage = localStorage)
                        }

                        composable("tela_instrucao1_screen") {
                            TelaInstrucao1Screen(navController = navController)
                        }

                        composable("tela_instrucao2_screen") {
                            TelaInstrucao2Screen(navController = navController)
                        }

                        composable("tela_instrucao3_screen") {
                            TelaInstrucao3Screen(navController = navController)
                        }

                        composable("main_screen") {
                            MainScreen(navRotasController = navController, localStorage = localStorage)
                        }

                        //SETTING
                        composable("setting_screen"){
                            SettingsScreen(navController = navController, navRotasController = navController)
                        }

                        composable("notification_screen"){
                            NotificationScreen(navController)
                        }

                        composable("codigo_paciente_screen"){
                            PatientCodeScreen(navController = navController, navRotasController = navController)
                        }

                        composable("profile_caregiver_screen"){
                            ProfileCaregiverScreen(navController = navController, localStorage = localStorage)
                        }

                        composable("linked_accounts_screen"){
                            LinkedAccountsScreen(navController = navController, lifecycleScope = lifecycleScope, localStorage = localStorage)
                        }

                        composable("sugestoes_screen"){
                            SuggestionScreen(navController = navController, navRotasController = navController, lifecycleScope = lifecycleScope )
                        }

                        //RELATORIO
                        composable("relatorios_screen") {
                            RelatoriosScreen(navController = navController, localStorage)
                        }

                        composable("relatorio_screen") {
                            RelatorioScreen(navController = navController, localStorage)
                        }

                        composable("relatorios_paciente_screen") {
                            RelatoriosByIdPacienteScreen(navController = navController, localStorage)
                        }

                        composable("relatorio_paciente_screen") {
                            RelatorioByIdPacienteScreen(navController = navController, localStorage)
                        }

                        composable("add_relatorio_screen") {
                            AddRelatorioScreen(navController = navController,lifecycleScope = lifecycleScope,localStorage)
                        }

                        composable("question_screen") {
                            QuestionScreen(navController = navController, localStorage, lifecycleScope = lifecycleScope)
                        }

                        composable("view_question_screen") {
                            ViewQuestionScreen(navController = navController, localStorage)
                        }

                        composable("add_question_screen") {
                            AddQuestionScreen(navController = navController,localStorage,lifecycleScope = lifecycleScope )
                        }

                        //RELATORIO HUMOR
                        composable("relatorios_humor_screen") {
                            RelatoriosHumorScreen(navController = navController, localStorage)
                        }

                        composable("relatorio_humor_screen") {
                            RelatorioHumorScreen(navController = navController, localStorage)
                        }

                        //PERFIL
                        composable("edit_profile_screen"){
                            EditProfileScreen(navController = navController, navRotasController = navController, lifecycleScope = lifecycleScope)
                        }


                        //PACIENTES
                        composable("Calendar_screen"){
                            CalendaryScreen(navController = navController, navController,localStorage, lifecycleScope)
                        }

                        composable("event_screen"){
                            EventScreen(navController = navController,lifecycleScope = lifecycleScope, localStorage )
                        }

                    }
                }
            }
        }
    }
}