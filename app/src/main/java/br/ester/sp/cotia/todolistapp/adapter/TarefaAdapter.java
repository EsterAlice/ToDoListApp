package br.ester.sp.cotia.todolistapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

import br.ester.sp.cotia.todolistapp.R;
import br.ester.sp.cotia.todolistapp.model.Tarefa;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder> {
    //variável para lista de tarefas
    private List<Tarefa> tarefas;
    // variável para o Context
    private Context context;

    //construtor que recebe os parâmetros para o Adapter
    public TarefaAdapter(List<Tarefa> lista, Context contexto){
        this.tarefas = lista;
        this.context = contexto;
    }

    @NonNull
    @Override
    public TarefaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflar a view do viewHolder
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_tarefas, parent, false);
       // retorna uma viewHolder
        return new TarefaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TarefaViewHolder holder, int position) {
        //obter a tarefa através da position
        Tarefa t = tarefas.get(position);
        // transportar a info da tarefa para o holder
        holder.tvTitulo.setText(t.getTitulo());
        //formata a data e exibe no textView
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        holder.tvData.setText(formatador.format(t.getDataPrevista()));
        //verifica se está concluida
        if (t.isConcluida()){
            holder.tvstatus.setText("finalizada");
            holder.tvstatus.setBackgroundColor(context.getResources().getColor(R.color.green));
        }else{
            holder.tvstatus.setText("Aberta");
            holder.tvstatus.setBackgroundColor(context.getResources().getColor(R.color.yellow));
        }


    }

    @Override
    public int getItemCount() {
        if (tarefas != null){
            return tarefas.size();
        }
        return 0;
    }

    class TarefaViewHolder extends RecyclerView.ViewHolder{
           //variáveis para os componentes do Layout
           TextView tvTitulo, tvData, tvstatus;

           public TarefaViewHolder(View view){
               super(view);
               // passar da view para os componentes
               tvTitulo = view.findViewById(R.id.tarefa);
               tvData = view.findViewById(R.id.data_prevista);
               tvstatus = view.findViewById(R.id.nome_status);
           }
       }
}
