using UnityEngine;

public class Cube : Shape
{
    protected override void Upgrade()
    {
        this.impact += 1;
    }
}
