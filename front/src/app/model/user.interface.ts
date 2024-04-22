import { Subscription } from "./subscription.interface";

export interface User {
    id: number;
    email: string;
    username: string;
    password: string;
    createdAt?: Date;
    updatedAt?: Date;
    subscriptions: Subscription[];
}
