using UnityEngine;

public class Ball : Shape
{
    protected override void Upgrade()
    {
        this.speed += 1;
    }
}
