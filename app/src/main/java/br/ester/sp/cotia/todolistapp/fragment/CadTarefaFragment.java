package br.ester.sp.cotia.todolistapp.fragment;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.time.Year;
import java.util.Calendar;

import br.ester.sp.cotia.todolistapp.R;
import br.ester.sp.cotia.todolistapp.database.AppDatabase;
import br.ester.sp.cotia.todolistapp.databinding.FragmentCadTarefaBinding;
import br.ester.sp.cotia.todolistapp.model.Tarefa;


public class CadTarefaFragment extends Fragment {
    private FragmentCadTarefaBinding binding;
    private DatePickerDialog datePicker;
    //variavel para ano, mês e dia
    int year, month, day;
    // variavel para obter a data atual
    Calendar dataAtual;
    // variavel para a data formatada
    String dataFormatada = "";
    // variável para a database
    AppDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //instancia a database
        database = AppDatabase.getDatabase(getContext());
        //instanciar o binding
        binding = FragmentCadTarefaBinding.inflate(getLayoutInflater(), container, false);

        // instanciar a data atual
        dataAtual = Calendar.getInstance();
        // obter ano, mês e dia, da data atual
        year = dataAtual.get(Calendar.YEAR);
        month = dataAtual.get(Calendar.MONTH);
        day = dataAtual.get(Calendar.DAY_OF_MONTH);

        //instaciar o datePicker
        datePicker = new DatePickerDialog(getContext(), (datePicker, ano, mes, dia) -> {
            // ao escolher uma data no datePicker, cai aqui
            //passar para variáveis globais
            year = ano;
            month = mes;
            day = dia;
            // formata a data
            dataFormatada = String.format("%02d/%02d/%04d", day, month + 1, year);
            // aplica a data formatada no botão
            binding.buttonData.setText(dataFormatada);
        }, year, month, day);

        // ação do click do botão de seleção da data
        binding.buttonData.setOnClickListener(v ->{
            datePicker.show();
        });

        // listener do botão salvar
        binding.buttonSalvar.setOnClickListener(v -> {
        if(binding.editTitulo.getText().toString().isEmpty()) {
            Snackbar.make(binding.editTitulo, R.string.nome_titulo, Snackbar.LENGTH_SHORT).show();
        }else if (dataFormatada.isEmpty()){
            Snackbar.make(binding.buttonData, R.string.nome_data, Snackbar.LENGTH_SHORT).show();
        }else {
            // criar uma tarefa
            Tarefa tarefa = new Tarefa();
            // popular o objeto tarefa
            tarefa.setTitulo(binding.editTitulo.getText().toString());
            tarefa.setDescricao(binding.editDescricao.getText().toString());
            tarefa.setDataCriacao(dataAtual.getTimeInMillis());
            // criar um Calendar
            Calendar dataPrevista = Calendar.getInstance();
            // muda a data para a data escolhida no datepicker
            dataPrevista.set(year, month, day);
            //passa os milisegundos da data para a data prevista
            tarefa.setDataPrevista(dataPrevista.getTimeInMillis());
            // salvar a tarefa
            new InsertTarefa().execute(tarefa);


        }

        });


        // retorna a view raiz (root) do binding
        return binding.getRoot();
    }

    // AsyncTask para inserir Tarefa
    private class InsertTarefa extends AsyncTask<Tarefa, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Tarefa... tarefas) {
            //pegar a tarefa a partir do vetor
            Tarefa t = tarefas[0];
            try {
                // chamar o método para salvar a tarefa
                database.getTarefaDao().insert(t);
                //retorna
                return "ok";
            }catch (Exception erro){
                erro.printStackTrace();
                //retorna a mensagem de erro
                return erro.getMessage();
            }

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.equals("ok")){
                Log.w("RESULT", "IUPIIIII TAREFA INSERIDA COM SUCESSO");
                Toast.makeText(getContext(), "Tarefa inserida com sucesso", Toast.LENGTH_SHORT).show();
                // voltar ao fragment anterior
                getActivity().onBackPressed();
            }else{
                Log.w("RESULT", result);
                Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
            }

        }
    }
}