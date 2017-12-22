package br.com.app.meucadastro.helper;

import android.widget.EditText;
import android.widget.SeekBar;

import br.com.app.meucadastro.MainActivityFormulario;
import br.com.app.meucadastro.R;
import br.com.app.meucadastro.modelo.bean.Aluno;

/**
 * Created by Carlos on 14/11/2017.
 */

public class FormularioHelper {

    private EditText nome;
    private EditText telefone;
    private EditText endereco;
    private EditText email;
    private SeekBar nota;

    private Aluno aluno;


    public FormularioHelper(MainActivityFormulario activity){

        //Asociação dos campos..
        nome = (EditText)activity.findViewById(R.id.edtNome);
        telefone = (EditText)activity.findViewById(R.id.edtTelefone);
        endereco = (EditText)activity.findViewById(R.id.edtEndereco);
        email = (EditText)activity.findViewById(R.id.edtEmail);
        nota = (SeekBar)activity.findViewById(R.id.sbNota);

        aluno = new Aluno();


    }

    public Aluno getAluno(){

        aluno.setNome(nome.getText().toString());
        aluno.setTelefone(telefone.getText().toString());
        aluno.setEndereco(endereco.getText().toString());
        aluno.setEmail(email.getText().toString());
        aluno.setNota(Double.valueOf(nota.getProgress()));
        return aluno;


    }

    public void setAluno(Aluno aluno){
        nome.setText(aluno.getNome());
        telefone.setText(aluno.getTelefone());
        endereco.setText(aluno.getEndereco());
        email.setText(aluno.getEmail());
        nota.setProgress(aluno.getNota().intValue());

        this.aluno = aluno;
    }
}
