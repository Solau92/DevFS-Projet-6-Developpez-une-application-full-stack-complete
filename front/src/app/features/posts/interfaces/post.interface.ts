import { User } from "src/app/interfaces/user.interface";

export interface Post {
    id: number,
    auteur: User,
    topic_id: number,
    title: string;
    content: string,
    createdAt?: Date,
    updatedAt?: Date
}