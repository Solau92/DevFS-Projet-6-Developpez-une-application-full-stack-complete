import { Topic } from "../features/topics/interfaces/topic.interface";
import { User } from "./user.interface";

export interface Subscription {
    id: number,
    user: User,
    topic: Topic
}

// export interface Subscription {
//     id: number,
//     user_id: string,
//     topic_id: string
// }