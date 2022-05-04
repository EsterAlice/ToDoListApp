package br.ester.sp.cotia.todolistapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import br.ester.sp.cotia.todolistapp.R;
import br.ester.sp.cotia.todolistapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    //variáveis para configurar o appBar
    private AppBarConfiguration appBarConfiguration;
    private NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //instancia o binding
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        //seta na contentView a raiz (root) do binding
        setContentView(binding.getRoot());
        // configurando a navigation com app bar
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
        || super.onSupportNavigateUp();
    }
}