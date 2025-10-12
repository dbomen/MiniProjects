export type InputTypeOf<T> = T extends (first: infer I, ...args: any[]) => any ? I : unknown;

type Expect<T extends true> = T;
type Equal<X, Y> =
    (<T>() => T extends X ? 1 : 2) extends <T>() => T extends Y ? 1 : 2
    ? true
    : false;

type SignupForm = {
    name: string;
    email: string;
};

function submitForm(_: SignupForm): boolean {
    return true;
}

function resetForm(): void { }

type _Test1 = Expect<Equal<InputTypeOf<typeof submitForm>, SignupForm>>;
type _Test2 = Expect<Equal<InputTypeOf<typeof resetForm>, unknown>>;
type _Test3 = Expect<Equal<InputTypeOf<null>, unknown>>;
type _Test4 = Expect<Equal<InputTypeOf<number>, unknown>>;
type _Test5 = Expect<
    Equal<InputTypeOf<(input: { foo: string }) => void>, { foo: string }>
>;

console.log("✓ Pass: TSC Compiled");
