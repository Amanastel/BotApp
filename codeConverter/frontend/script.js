const convertButton = document.getElementById('convertButton');
const inputCode = document.getElementById('inputCode');
const outputCode = document.getElementById('outputCode');
const languageSelect = document.getElementById('languageSelect');

convertButton.addEventListener('click', async () => {
  const code = inputCode.value;
  const selectedLanguage = languageSelect.value; // Get the selected language from the dropdown

  try {
    const response = await fetch('/convert', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ code, language: selectedLanguage }) // Send selected language to the backend
    });

    const data = await response.json();
    outputCode.textContent = data.convertedCode;
  } catch (error) {
    console.error('Error converting code:', error);
  }
});
