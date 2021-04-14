package com.ifsp.pdm.emanoela.sharedjobs;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ifsp.pdm.emanoela.sharedjobs.databinding.ActivityMainBinding;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());


        activityMainBinding.celularCB.setOnClickListener(v -> {
            if (((CompoundButton) v).isChecked()) {
                activityMainBinding.celularET.setVisibility(View.VISIBLE);
            } else {
                activityMainBinding.celularET.setVisibility(View.GONE);
            }
        });

        activityMainBinding.formacaoSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selecao = (String) ((TextView) view).getText();
                switch (selecao) {
                    case "Ensino Fundamental":
                    case "Ensino Médio":
                        activityMainBinding.perguntasFundEMedLL.setVisibility(View.VISIBLE);
                        activityMainBinding.perguntasSuperiorLL.setVisibility(View.GONE);
                        activityMainBinding.perguntasMestEDoutLL.setVisibility(View.GONE);
                        break;
                    case "Graduação":
                    case "Especialização":
                        activityMainBinding.perguntasSuperiorLL.setVisibility(View.VISIBLE);
                        activityMainBinding.perguntasFundEMedLL.setVisibility(View.GONE);
                        activityMainBinding.perguntasMestEDoutLL.setVisibility(View.GONE);
                        break;
                    case "Mestrado":
                    case "Doutorado":
                        activityMainBinding.perguntasSuperiorLL.setVisibility(View.VISIBLE);
                        activityMainBinding.perguntasMestEDoutLL.setVisibility(View.VISIBLE);
                        activityMainBinding.perguntasFundEMedLL.setVisibility(View.GONE);
                        break;
                    default:
                        activityMainBinding.perguntasFundEMedLL.setVisibility(View.GONE);
                        activityMainBinding.perguntasSuperiorLL.setVisibility(View.GONE);
                        activityMainBinding.perguntasMestEDoutLL.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
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
        activityMainBinding.nomeCompletoET.setText("");
        activityMainBinding.emailET.setText("");
        activityMainBinding.emailCB.setChecked(false);
        activityMainBinding.tipoTelefoneSP.setSelection(0);
        activityMainBinding.telefoneET.setText("");
        activityMainBinding.celularCB.setChecked(false);
        activityMainBinding.celularET.setText("");
        activityMainBinding.femininoRB.setChecked(true);
        activityMainBinding.dataNascDP.updateDate(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
        activityMainBinding.formacaoSP.setSelection(0);
        activityMainBinding.fundEMedioAnoFormaturaET.setText("");
        activityMainBinding.superiorConclusaoaET.setText("");
        activityMainBinding.superiorInstituicaoET.setText("");
        activityMainBinding.mestEDoutTituloET.setText("");
        activityMainBinding.mestEDoutOrientadorET.setText("");
        activityMainBinding.vagasET.setText("");
    }

    private void saveForm() {
        StringBuilder sumarioSB = new StringBuilder();
        sumarioSB.append("Nome completo: ").append(activityMainBinding.nomeCompletoET.getText().toString()).append("\n");
        sumarioSB.append("E-mail: ").append(activityMainBinding.emailET.getText().toString()).append("\n");
        sumarioSB.append("Deseja receber e-mails com atualizações? ");

        if (activityMainBinding.emailCB.isChecked()) {
            sumarioSB.append("sim \n");
        } else {
            sumarioSB.append("não \n");
        }

        sumarioSB.append(((TextView) activityMainBinding.tipoTelefoneSP.getSelectedView()).getText()).append(": ");
        sumarioSB.append(activityMainBinding.telefoneET.getText().toString()).append("\n");

        if (activityMainBinding.celularCB.isChecked()) {
            sumarioSB.append("Celular: ").append(activityMainBinding.celularET.getText().toString()).append("\n");
        }

        sumarioSB.append("Sexo: ");
        switch (activityMainBinding.sexoRG.getCheckedRadioButtonId()) {
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
        int dia = activityMainBinding.dataNascDP.getDayOfMonth();
        int mes = activityMainBinding.dataNascDP.getMonth();
        int ano = activityMainBinding.dataNascDP.getYear();
        sumarioSB.append(dia).append("/").append(mes).append("/").append(ano).append("\n");

        String formacao = (String) ((TextView) activityMainBinding.formacaoSP.getSelectedView()).getText();
        sumarioSB.append("Formação: ").append(formacao).append("\n");

        switch (formacao) {
            case "Ensino Fundamental":
            case "Ensino Médio":
                sumarioSB.append("Ano de formatura: ").append(activityMainBinding.fundEMedioAnoFormaturaET.getText().toString());
                break;
            case "Graduação":
            case "Especialização":
                sumarioSB.append("Ano de conclusão: ").append(activityMainBinding.superiorConclusaoaET.getText().toString()).append("\n");
                sumarioSB.append("Instituição: ").append(activityMainBinding.superiorInstituicaoET.getText().toString());
                break;
            case "Mestrado":
            case "Doutorado":
                sumarioSB.append("Ano de conclusão: ").append(activityMainBinding.superiorConclusaoaET.getText().toString()).append("\n");
                sumarioSB.append("Instituição: ").append(activityMainBinding.superiorInstituicaoET.getText().toString()).append("\n");
                sumarioSB.append("Título de monografia: ").append(activityMainBinding.mestEDoutTituloET.getText().toString()).append("\n");
                sumarioSB.append("Orientador: ").append(activityMainBinding.mestEDoutOrientadorET.getText().toString());
                break;
            default:
                break;
        }
        sumarioSB.append("\n");
        sumarioSB.append("Vagas de interesse: ").append(activityMainBinding.vagasET.getText().toString());

        Toast.makeText(this, sumarioSB.toString(), Toast.LENGTH_LONG).show();
        Toast.makeText(this, sumarioSB.toString(), Toast.LENGTH_LONG).show();
    }
}