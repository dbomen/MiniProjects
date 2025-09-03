using UnityEngine;

public class GameManager : MonoBehaviour
{
    private static GameManager instance;
    public static GameManager Instance
    { 
        get
        {
            if (instance == null)
            {
                instance = new GameObject().AddComponent<GameManager>();
                instance.name = instance.GetType().ToString();
                Object.DontDestroyOnLoad(instance.gameObject);
            }
            return instance;
        }
    }

    public string playerName;
}
