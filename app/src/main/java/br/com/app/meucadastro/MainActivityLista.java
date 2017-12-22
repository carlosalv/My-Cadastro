package br.com.app.meucadastro;

import android.app.LauncherActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.com.app.meucadastro.modelo.bean.Aluno;
import br.com.app.meucadastro.modelo.bean.dao.AlunoDao;

public class MainActivityLista extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView ListaAlunos;
    //
    private List<Aluno>listaAlunos;
    //
    private ArrayAdapter<Aluno> adapter;
    private Aluno alunoSelecionado;



    //
    private int adapterLayout = android.R.layout.simple_list_item_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lista);

        //

        ListaAlunos = (ListView)findViewById(R.id.lstAlunos);



            ListaAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int args2, long args3) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityLista.this);
                    builder.setTitle("Confirmação");
                    builder.setMessage("Deseja realmente deletar este aluno?");
                    builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AlunoDao dao = new AlunoDao(MainActivityLista.this);
                            dao.deletar(alunoSelecionado);
                            dao.close();
                            carregarLista();
                        }
                    });
                    builder.setNegativeButton("Não", null);
                    AlertDialog dialog = builder.create();
                    dialog.show();

                    return false;


                }
            });
        //
        ListaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int posicao, long id) {
                Intent form = new Intent(MainActivityLista.this, MainActivityFormulario.class);
                alunoSelecionado = (Aluno) ListaAlunos.getItemAtPosition(posicao);

                form.putExtra("ALUNO_SELECIONADO", alunoSelecionado);
                startActivity(form);



            }
        });


        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Alunos");
        setSupportActionBar(toolbar);
    }

    //
    private void carregarLista(){

        //
        AlunoDao dao = new AlunoDao(this);

        //
        this.listaAlunos = dao.listar();

        //
        dao.close();

        //
        this.adapter = new ArrayAdapter<Aluno>(this, adapterLayout, listaAlunos);
        //
        this.ListaAlunos.setAdapter(adapter);
    }
    @Override
    protected void onResume(){
        super.onResume();
        this.carregarLista();

    }



    //TOOLBAR
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_lista, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case  R.id.btnAdicionar:

                Intent it = new Intent(MainActivityLista.this, MainActivityFormulario.class);
                startActivity(it);

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
