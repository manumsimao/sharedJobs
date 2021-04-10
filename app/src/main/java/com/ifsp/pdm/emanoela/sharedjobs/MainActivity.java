package com.ifsp.pdm.emanoela.sharedjobs;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText nomeCompletoET;
    private EditText emailET;
    private CheckBox emailCB;
    private Spinner tipoTelefoneSP;
    private EditText telefoneET;
    private CheckBox celularCB;
    private EditText celularET;
    private RadioGroup sexoRG;
    private RadioButton femininoRB;
    private DatePicker dataNascDP;
    private Spinner formacaoSP;
    private LinearLayout perguntasFundEMedLL;
    private EditText fundEMedioAnoFormaturaET;
    private LinearLayout perguntasSuperiorLL;
    private EditText superiorConclusaoaET;
    private EditText superiorInstituicaoET;
    private LinearLayout perguntasMestEDoutLL;
    private EditText mestEDoutTituloET;
    private EditText mestEDoutOrientadorET;
    private EditText vagasET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();


        celularCB.setOnClickListener(v -> {
            if (((CompoundButton) v).isChecked()) {
                celularET.setVisibility(View.VISIBLE);
            } else {
                celularET.setVisibility(View.GONE);
            }
        });

        formacaoSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selecao = (String) ((TextView) view).getText();
                switch (selecao) {
                    case "Ensino Fundamental":
                    case "Ensino Médio":
                        perguntasFundEMedLL.setVisibility(View.VISIBLE);
                        perguntasSuperiorLL.setVisibility(View.GONE);
                        perguntasMestEDoutLL.setVisibility(View.GONE);
                        break;
                    case "Graduação":
                    case "Especialização":
                        perguntasSuperiorLL.setVisibility(View.VISIBLE);
                        perguntasFundEMedLL.setVisibility(View.GONE);
                        perguntasMestEDoutLL.setVisibility(View.GONE);
                        break;
                    case "Mestrado":
                    case "Doutorado":
                        perguntasSuperiorLL.setVisibility(View.VISIBLE);
                        perguntasMestEDoutLL.setVisibility(View.VISIBLE);
                        perguntasFundEMedLL.setVisibility(View.GONE);
                        break;
                    default:
                        perguntasFundEMedLL.setVisibility(View.GONE);
                        perguntasSuperiorLL.setVisibility(View.GONE);
                        perguntasMestEDoutLL.setVisibility(View.GONE);
                        break;
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void bindViews() {
        nomeCompletoET = findViewById(R.id.nomeCompletoET);
        emailET = findViewById(R.id.emailET);
        emailCB = findViewById(R.id.emailCB);
        tipoTelefoneSP = findViewById(R.id.tipoTelefoneSP);
        telefoneET = findViewById(R.id.telefoneET);
        celularCB = findViewById(R.id.celularCB);
        celularET = findViewById(R.id.celularET);
        sexoRG = findViewById(R.id.sexoRG);
        femininoRB = findViewById(R.id.femininoRB);
        dataNascDP = findViewById(R.id.dataNascDP);
        formacaoSP = findViewById(R.id.formacaoSP);
        perguntasFundEMedLL = findViewById(R.id.perguntasFundEMedLL);
        fundEMedioAnoFormaturaET = findViewById(R.id.fundEMedioAnoFormaturaET);
        perguntasSuperiorLL = findViewById(R.id.perguntasSuperiorLL);
        superiorConclusaoaET = findViewById(R.id.superiorConclusaoaET);
        superiorInstituicaoET = findViewById(R.id.superiorInstituicaoET);
        perguntasMestEDoutLL = findViewById(R.id.perguntasMestEDoutLL);
        mestEDoutTituloET = findViewById(R.id.mestEDoutTituloET);
        mestEDoutOrientadorET = findViewById(R.id.mestEDoutOrientadorET);
        vagasET = findViewById(R.id.vagasET);
    }


    public void onClickButton(View view) {
        switch (view.getId()) {
            case R.id.salvarBTN:
                saveForm();
                break;
            case R.id.limparBTN:
                cleanForm();
                break;
            default:
                break;
        }
    }

    private void cleanForm() {
        Calendar now = Calendar.getInstance();
        nomeCompletoET.setText("");
        emailET.setText("");
        emailCB.setChecked(false);
        tipoTelefoneSP.setSelection(0);
        telefoneET.setText("");
        celularCB.setChecked(false);
        celularET.setText("");
        femininoRB.setChecked(true);
        dataNascDP.updateDate(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
        formacaoSP.setSelection(0);
        fundEMedioAnoFormaturaET.setText("");
        superiorConclusaoaET.setText("");
        superiorInstituicaoET.setText("");
        mestEDoutTituloET.setText("");
        mestEDoutOrientadorET.setText("");
        vagasET.setText("");
    }

    private void saveForm() {
        StringBuilder sumarioSB = new StringBuilder();
        sumarioSB.append("Nome completo: ").append(nomeCompletoET.getText().toString()).append("\n");
        sumarioSB.append("E-mail: ").append(emailET.getText().toString()).append("\n");
        sumarioSB.append("Deseja receber e-mails com atualizações? ");

        if (emailCB.isChecked()) {
            sumarioSB.append("sim \n");
        } else {
            sumarioSB.append("não \n");
        }

        sumarioSB.append(((TextView) tipoTelefoneSP.getSelectedView()).getText()).append(": ");
        sumarioSB.append(telefoneET.getText().toString()).append("\n");

        if (celularCB.isChecked()) {
            sumarioSB.append("Celular: ").append(celularET.getText().toString()).append("\n");
        }

        sumarioSB.append("Sexo: ");
        switch (sexoRG.getCheckedRadioButtonId()) {
            case R.id.femininoRB:
                sumarioSB.append("feminino \n");
                break;
            case R.id.masculinoRB:
                sumarioSB.append("masculino \n");
                break;
            default:
                break;
        }

        sumarioSB.append("Data de nascimento: ");
        int dia = dataNascDP.getDayOfMonth();
        int mes = dataNascDP.getMonth();
        int ano = dataNascDP.getYear();
        sumarioSB.append(dia).append("/").append(mes).append("/").append(ano).append("\n");

        String formacao = (String) ((TextView) formacaoSP.getSelectedView()).getText();
        sumarioSB.append("Formação: ").append(formacao).append("\n");

        switch (formacao) {
            case "Ensino Fundamental":
            case "Ensino Médio":
                sumarioSB.append("Ano de formatura: ").append(fundEMedioAnoFormaturaET.getText().toString());
                break;
            case "Graduação":
            case "Especialização":
                sumarioSB.append("Ano de conclusão: ").append(superiorConclusaoaET.getText().toString()).append("\n");
                sumarioSB.append("Instituição: ").append(superiorInstituicaoET.getText().toString());
                break;
            case "Mestrado":
            case "Doutorado":
                sumarioSB.append("Ano de conclusão: ").append(superiorConclusaoaET.getText().toString()).append("\n");
                sumarioSB.append("Instituição: ").append(superiorInstituicaoET.getText().toString()).append("\n");
                sumarioSB.append("Título de monografia: ").append(mestEDoutTituloET.getText().toString()).append("\n");
                sumarioSB.append("Orientador: ").append(mestEDoutOrientadorET.getText().toString());
                break;
            default:
                break;
        }
        sumarioSB.append("\n");
        sumarioSB.append("Vagas de interesse: ").append(vagasET.getText().toString());

        Toast.makeText(this, sumarioSB.toString(), Toast.LENGTH_LONG).show();
        Toast.makeText(this, sumarioSB.toString(), Toast.LENGTH_LONG).show();
    }
}