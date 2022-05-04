package br.ester.sp.cotia.todolistapp.fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.nio.channels.AsynchronousChannelGroup;
import java.util.List;

import br.ester.sp.cotia.todolistapp.R;
import br.ester.sp.cotia.todolistapp.adapter.TarefaAdapter;
import br.ester.sp.cotia.todolistapp.database.AppDatabase;
import br.ester.sp.cotia.todolistapp.databinding.FragmentPrincipalBinding;
import br.ester.sp.cotia.todolistapp.model.Tarefa;

public class PrincipalFragment extends Fragment {
private FragmentPrincipalBinding binding;
//variável para a lista
   private  List<Tarefa> tarefas;
    //variável para o Adapter
   private TarefaAdapter adapter;
    //variável para database
   private AppDatabase database;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //instanciar o binding
        binding = FragmentPrincipalBinding.inflate(getLayoutInflater(), container, false);

        binding.btNovaTarefa.setOnClickListener(v -> {
            NavHostFragment.findNavController(PrincipalFragment.this).navigate(R.id.action_principalFragment_to_cadTarefaFragment);
        });

        // retorna a view raiz (root) do binding
        return binding.getRoot();
    }

    class  ReadTarefa extends AsyncTask<Void, Void, List<Tarefa>>{

        @Override
        protected List<Tarefa> doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPostExecute(List<Tarefa> tarefas) {
            super.onPostExecute(tarefas);
        }
    }
}