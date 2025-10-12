type FilterNumberKeys<T> = {
    [K in keyof T]: T[K] extends number ? K : never
};
export type NumberKeys<T> = FilterNumberKeys<T>[keyof T]

type Expect<T extends true> = T;
type Equal<X, Y> =
    (<T>() => T extends X ? 1 : 2) extends <T>() => T extends Y ? 1 : 2
    ? true
    : false;

type _Test1 = Expect<
    Equal<
        NumberKeys<{
            score: number;
            label: string;
            confidence: number;
            active: boolean;
        }>,
        "score" | "confidence"
    >
>;

type _Test2 = Expect<Equal<NumberKeys<{}>, never>>;

type _Test3 = Expect<
    Equal<NumberKeys<{ one: number; two: string; three: boolean }>, "one">
>;

type _Test4 = Expect<
    Equal<NumberKeys<{ a: string; b: boolean; c: () => void }>, never>
>;

console.log("✓ Pass: TSC Compiled");
