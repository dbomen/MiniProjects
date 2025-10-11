export interface Config {
    apiUrl: string;
    timeout: number;
    debug: boolean;
}

export function importConfig(config: Readonly<Config>) {
    return config;
}
