export type EditableFields<T> = {
    [K in keyof T]: T[K] extends Function | object ? never : T[K]
};
