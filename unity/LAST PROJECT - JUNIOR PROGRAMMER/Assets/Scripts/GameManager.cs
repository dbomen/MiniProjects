using System.Collections;
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

    private bool isGameActive;
    private string playerName;

    public void StartGame(string playerName)
    {
        this.playerName = playerName;
        this.isGameActive = true;

        StartCoroutine(GetSpawners());
    }

    public void StopGame()
    {
        this.isGameActive = false;
    }

    private IEnumerator GetSpawners()
    {
        float timeToWaitForSceneToLoad = 2.0f;
        yield return new WaitForSeconds(timeToWaitForSceneToLoad);
        Spawner ballSpawner = GameObject.FindGameObjectWithTag("Ball Spawner").GetComponent<Spawner>();
        Spawner cubeSpawner = GameObject.FindGameObjectWithTag("Cube Spawner").GetComponent<Spawner>();
        ballSpawner.Spawn();
        cubeSpawner.Spawn();
    }
}
