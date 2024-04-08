import { User } from "src/app/interfaces/user.interface";
import { Topic } from "../../topics/interfaces/topic.interface";

export interface Post {
    id: number,
    auteur: User,
    topic_id: number,
    title: string;
    content: string,
    createdAt?: Date,
    updatedAt?: Date
    topic?: Topic;
}