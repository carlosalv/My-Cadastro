package br.com.app.meucadastro;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import br.com.app.meucadastro.helper.FormularioHelper;
import br.com.app.meucadastro.modelo.bean.Aluno;
import br.com.app.meucadastro.modelo.bean.dao.AlunoDao;

public class MainActivityFormulario extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText Nome;
    private EditText Telefone;
    private EditText Email;
    private EditText Endereco;
    private SeekBar Nota;
    private FormularioHelper helper;
    private Aluno alunoParaSerAlterado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_formulario);

        //Criação do objeto Helper
        helper = new FormularioHelper(this);

        //
        alunoParaSerAlterado = (Aluno) getIntent().getSerializableExtra("ALUNO_SELECIONADO");


        if (alunoParaSerAlterado!=null){
            helper.setAluno(alunoParaSerAlterado);
        }



            //Asociação dos componentes
        Nome = (EditText)findViewById(R.id.edtNome);
        Telefone = (EditText)findViewById(R.id.edtTelefone);
        Email = (EditText)findViewById(R.id.edtEmail);
        Endereco = (EditText)findViewById(R.id.edtEndereco);
        Nota = (SeekBar)findViewById(R.id.sbNota);



        //Definição do novo objeto aluno..
        Aluno aluno = new Aluno();

        //Pegar dados da tela e setar nos atributos de Aluno
        aluno.setNome(Nome.getText().toString());
        aluno.setTelefone(Telefone.getText().toString());
        aluno.setEmail(Email.getText().toString());
        aluno.setEndereco(Endereco.getText().toString());
        aluno.setNota(Double.valueOf(Nota.getProgress()));



        //Configurações da TOOLBAR...
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setTitle("Cadastro de Aluno");
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater Inflater = getMenuInflater();
        Inflater.inflate(R.menu.menu_formulario, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()){
            case R.id.btnSalvar:


                //utilização da helper
                Aluno aluno = helper.getAluno();



                //Criação do objeto DAO conexao com o BD
                AlunoDao dao = new AlunoDao(MainActivityFormulario.this);

                //chamada do metodo de cadastro do aluno

                if (aluno.getId()==null) {

                    dao.cadastra(aluno);
                }else{
                    dao.alterar(aluno);
                }
                //
                dao.close();

                //
                finish();

               // Toast.makeText(MainActivityFormulario.this,
                     //   aluno.getNome(),Toast.LENGTH_LONG).show();
               // finish();

            // break;
        }

        return super.onOptionsItemSelected(item);
    }
}
