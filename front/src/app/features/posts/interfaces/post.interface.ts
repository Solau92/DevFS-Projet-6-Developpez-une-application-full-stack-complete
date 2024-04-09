import { User } from "src/app/interfaces/user.interface";
import { Topic } from "../../topics/interfaces/topic.interface";
import { Comment } from '../interfaces/comment.interface';

export interface Post {
    id: number,
    auteur: User,
    topic_id: number,
    title: string;
    content: string,
    createdAt?: Date,
    updatedAt?: Date
    topic?: Topic;
    comments: Comment[];
}