using System.Collections;
using System.Collections.Generic;
using Unity.VisualScripting;
using UnityEngine;

public class Spawner : MonoBehaviour
{
    [SerializeField]
    private Cube cubePrefab;
    [SerializeField]
    private Ball ballPrefab;

    private List<Cube> cubeList;
    private List<Ball> ballList;

    private int NUMBER_TO_SPAWN = 10;

    void Start()
    {
        this.cubeList = new List<Cube>();
        this.ballList = new List<Ball>();
    }

    public void Spawn()
    {
        StartCoroutine(StartSpawning());
    }

    private Vector3 GetRandomSpawnPosition()
    {
        return GetRandomSpawnPosition(0f);
    }
    private Vector3 GetRandomSpawnPosition(float xOffSet)
    {
        float zRange = 10;
        float xRange = 5;

        float z = this.gameObject.transform.position.z + Random.Range(-zRange, zRange);
        float x = xOffSet + this.gameObject.transform.position.x + Random.Range(-xRange, xRange);

        return new Vector3(x, this.gameObject.transform.position.y, z);
    }

    private IEnumerator StartSpawning()
    {

        float ballXPositionOffset = 20f;
        for (int i = 0; i < NUMBER_TO_SPAWN; i++)
        {
            cubeList.Add(Instantiate(cubePrefab, GetRandomSpawnPosition(), cubePrefab.transform.rotation));
            ballList.Add(Instantiate(ballPrefab, GetRandomSpawnPosition(ballXPositionOffset), ballPrefab.transform.rotation));

            yield return new WaitForSeconds(0.5f);
        }

        for (int i = 0; i < NUMBER_TO_SPAWN; i++)
        {
            cubeList[i].OnCreate(this);
            ballList[i].OnCreate(this);
        }
    }

    public Vector3 GetDirectionVectorToClosestCube(Vector3 currentPosition)
    {
        return GetDirectionVectorToClosestInList(currentPosition, cubeList);
    }
    public Vector3 GetDirectionVectorToClosestBall(Vector3 currentPosition)
    {
        return GetDirectionVectorToClosestInList(currentPosition, ballList);
    }
    private Vector3 GetDirectionVectorToClosestInList<T>(Vector3 currentPosition, List<T> list) where T : Shape
    {
        int indexOfMinDistance = -1;
        float minDistance = float.MaxValue;

        for (int i = 0; i < list.Count; i++)
        {
            Vector3 cubePosition = list[i].gameObject.transform.position;
            float distance = (cubePosition - currentPosition).magnitude;
            if (distance < minDistance)
            {
                indexOfMinDistance = i;
                minDistance = distance;
            }
        }

        Vector3 minDistanceCubePosition = list[indexOfMinDistance].gameObject.transform.position;
        return (minDistanceCubePosition - currentPosition).normalized;
    }
}
