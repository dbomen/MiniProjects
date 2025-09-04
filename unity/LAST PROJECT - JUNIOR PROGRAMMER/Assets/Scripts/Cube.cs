using UnityEngine;

public class Cube : Shape
{
    protected override Vector3 GetDirectionVector()
    {
        return this.spawner.GetDirectionVectorToClosestBall(this.transform.position);
    }

    protected override void Upgrade()
    {
        this.impact += 1;
    }
}
