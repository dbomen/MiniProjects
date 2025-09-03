using TMPro.Examples;
using UnityEngine;

public abstract class Shape : MonoBehaviour
{
    [SerializeField]
    protected float speed;
    [SerializeField]
    protected float impact;

    private Spawner spawner;

    private void OnCreate(Spawner spawner)
    {
        this.spawner = spawner;
    }

    void FixedUpdate()
    {
        MoveToEnemy();
    }

    protected virtual void MoveToEnemy()
    {
        
    }

    private void ImpactOnCollision(Rigidbody enemyRigidbody)
    {
        enemyRigidbody.AddForce(new Vector3(0, impact, 0), ForceMode.Impulse);
    }

    protected abstract void Upgrade();

    private void OnCollisionEnter(Collision collision)
    {
        if (collision.gameObject.GetComponent<Shape>() != null)  
        {
            ImpactOnCollision(collision.gameObject.GetComponent<Rigidbody>());
        }
    }
}
