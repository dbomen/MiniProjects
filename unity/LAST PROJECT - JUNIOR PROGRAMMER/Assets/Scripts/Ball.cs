using UnityEngine;

public class Ball : Shape
{
    protected override Vector3 GetDirectionVector()
    {
        return this.spawner.GetDirectionVectorToClosestCube(this.transform.position);
    }
    protected override void Upgrade()
    {
        this.speed += 1;
    }
}
