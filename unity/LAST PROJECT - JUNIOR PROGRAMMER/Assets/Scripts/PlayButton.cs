using TMPro;
using UnityEngine;
using UnityEngine.SceneManagement;

public class PlayButton : MonoBehaviour
{
    public TMP_InputField inputField;

    public void Play()
    {
        string playerName = inputField.text;
        if (playerName.Length > 0)
        {
            GameManager.Instance.playerName = playerName;
            SceneManager.LoadScene(0);
        }
    }
}
