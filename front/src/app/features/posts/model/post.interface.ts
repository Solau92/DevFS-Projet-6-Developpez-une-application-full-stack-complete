import { User } from "src/app/model/user.interface";
import { Topic } from "../../topics/model/topic.interface";
import { Comment } from './comment.interface';

export interface Post {
    id: number,
    auteur: User,
    topic_id: number,
    title: string;
    content: string,
    createdAt: Date,
    updatedAt: Date
    topic?: Topic;
    comments: Comment[];
}