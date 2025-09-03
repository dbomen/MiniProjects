using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Spawner : MonoBehaviour
{
    [SerializeField]
    private Shape shapeType;
    [SerializeField]
    private Cube cubePrefab;
    [SerializeField]
    private Ball ballPrefab;

    private List<Shape> shapeList;

    private int NUMBER_TO_SPAWN = 10;

    void Start()
    {
        this.shapeList = new List<Shape>();
    }

    public void Spawn()
    {
        StartCoroutine(StartSpawning());
    }

    private Vector3 GetRandomSpawnPosition()
    {
        float zRange = 10;
        float xRange = 5;

        float z = this.gameObject.transform.position.z + Random.Range(-zRange, zRange);
        float x = this.gameObject.transform.position.x + Random.Range(-xRange, xRange);

        return new Vector3(x, this.gameObject.transform.position.y, z);
    }

    private IEnumerator StartSpawning()
    {
        for (int i = 0; i < NUMBER_TO_SPAWN; i++)
        {
            if (shapeType is Cube)
            {
                shapeList.Add(Instantiate(cubePrefab, GetRandomSpawnPosition(), cubePrefab.transform.rotation));
            }
            else if (shapeType is Ball)
            {
                shapeList.Add(Instantiate(ballPrefab, GetRandomSpawnPosition(), cubePrefab.transform.rotation));
            }
            else
            {
                throw new System.Exception("MISSING TYPE!");
            }

            yield return new WaitForSeconds(0.5f);
        }
    }
}
