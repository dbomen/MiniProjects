export type SentimentString<T> = T extends { angry: true } ? "mad" | "furious" : "content" | "happy";
