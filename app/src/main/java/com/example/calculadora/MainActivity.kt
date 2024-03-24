package com.example.calculadora

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    private lateinit var editTextNumber1: EditText
    private lateinit var editTextNumber2: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var buttonCalculate: Button
    private lateinit var textViewResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        editTextNumber1 = findViewById(R.id.editTextNumber1)
        editTextNumber2 = findViewById(R.id.editTextNumber2)
        radioGroup = findViewById(R.id.radioGroup)
        buttonCalculate = findViewById(R.id.buttonCalculate)
        textViewResult = findViewById(R.id.textViewResult)

        buttonCalculate.setOnClickListener {

            // Verifica se algum RadioButton está selecionado
            if (radioGroup.checkedRadioButtonId == -1) {
                // Nenhum RadioButton está selecionado
                showToast("Selecione uma operação")
                return@setOnClickListener  // Retorna sem realizar cálculos
            }

            // Se um RadioButton estiver selecionado, continue com o cálculo
            calculate()
        }

    }

    private fun calculate() {

        val num1 = editTextNumber1.text.toString()
        val num2 = editTextNumber2.text.toString()
        val selectedOperationId = radioGroup.checkedRadioButtonId

        //verifica se os dois numeros foram inseridos na textview
        if (num1.isEmpty() || num2.isEmpty()) {
            showToast("Por favor, insira ambos os números.")
            return
        }


        //após selecionar o radioButton faz a operação desejada
        val result =  when (findViewById<RadioButton>(selectedOperationId).text.toString()) {
            "Adição" -> soma()
            "Subtração" -> subtracao()
            "Multiplicação" -> mult()
            "Divisão" -> divisao()
            else -> 0.0
        }

        textViewResult.text = result.toString() //exibe o resultado na tela
    }

    //função de soma
    private fun soma(): Double {
        val num1 = editTextNumber1.text.toString().toDouble()
        val num2 = editTextNumber2.text.toString().toDouble()


        return num1 + num2
        }

    //função de subtração
    private fun subtracao(): Double {
        val num1 = editTextNumber1.text.toString().toDouble()
        val num2 = editTextNumber2.text.toString().toDouble()

        return num1 - num2
    }
    //função de multiplicação
    private fun mult() : Double{
        val num1 = editTextNumber1.text.toString().toDouble()
        val num2 = editTextNumber2.text.toString().toDouble()

       return num1 * num2
    }
    //função de divisão
    private fun divisao(): Double {
        val num1 = editTextNumber1.text.toString().toDouble()
        val num2 = editTextNumber2.text.toString().toDouble()
        //verifica se tenta dividir por 0, se for imprimi um toast de erro na tela
        if (num2 == 0.0) {
            showToast("Não é permitido divisão por 0. Tente Novamente")
        textViewResult.text = "Erro"
            return Double.NaN //retorna nan no label de resultado
        }
        return num1 / num2
    }
    //função do toast de erro
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}






