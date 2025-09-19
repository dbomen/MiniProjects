export function calculateTotal(
    price: number,
    quantity: number,
    discount: number,
): number {
    return price * quantity * (1 - discount);
}
