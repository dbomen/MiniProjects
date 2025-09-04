using TMPro.Examples;
using UnityEngine;

public abstract class Shape : MonoBehaviour
{
    [SerializeField]
    protected float speed;
    [SerializeField]
    protected float impact;

    protected Spawner spawner;

    private Rigidbody rb;

    public void OnCreate(Spawner spawner)
    {
        this.spawner = spawner;
        this.rb = GetComponent<Rigidbody>();
    }

    void FixedUpdate()
    {
        MoveToEnemy();
    }

    protected virtual void MoveToEnemy()
    {
        if (this.spawner != null)
        {
            Vector3 directionVector = GetDirectionVector();
            rb.AddForce(directionVector * this.speed, ForceMode.Force);
        }
    }

    private void ImpactOnCollision(Rigidbody enemyRigidbody)
    {
        enemyRigidbody.AddForce(new Vector3(0, impact, 0), ForceMode.Impulse);
    }

    protected abstract Vector3 GetDirectionVector();
    protected abstract void Upgrade();

    private void OnCollisionEnter(Collision collision)
    {
        if (collision.gameObject.GetComponent<Shape>() != null)  
        {
            ImpactOnCollision(collision.gameObject.GetComponent<Rigidbody>());
        }
    }
}
