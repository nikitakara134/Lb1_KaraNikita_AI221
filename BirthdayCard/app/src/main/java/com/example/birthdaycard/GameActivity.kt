package com.example.birthdaycard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.TextFieldValue

class GameActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WordScrambleGame()
        }
    }
}

@Composable
fun WordScrambleGame() {
    // Список слів для гри (цифри від 1 до 5 англійською мовою)
    val wordList = listOf("one", "two", "three", "four", "five")

    // Стан для поточного слова
    var currentWord by remember { mutableStateOf(wordList.random()) }

    // Перемішане слово
    val scrambledWord = currentWord.toCharArray().apply { shuffle() }.concatToString()

    // Текст введення користувача
    var userInput = remember { mutableStateOf(TextFieldValue()) }

    // Повідомлення результату
    var resultMessage = remember { mutableStateOf("") }

    // Графічний інтерфейс
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Виведення перемішаного слова
        Text(text = "Scrambled word: $scrambledWord", modifier = Modifier.padding(bottom = 16.dp))

        // Поле для введення слова
        BasicTextField(
            value = userInput.value,
            onValueChange = { userInput.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            textStyle = TextStyle(fontSize = 18.sp) // Використовуємо sp для шрифтів
        )

        // Кнопка для перевірки
        Button(
            onClick = {
                if (userInput.value.text.equals(currentWord, ignoreCase = true)) {
                    resultMessage.value = "Correct! Let's try again."
                    // Вибір нового слова після правильного відповіді
                    currentWord = wordList.random()
                    userInput.value = TextFieldValue("")  // Очищаємо поле вводу
                } else {
                    resultMessage.value = "Incorrect. Try again."
                }
            },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(text = "Check")
        }

        // Повідомлення про результат
        Text(text = resultMessage.value)
    }
}

@Preview(showBackground = true)
@Composable
fun GamePreview() {
    WordScrambleGame()
}


