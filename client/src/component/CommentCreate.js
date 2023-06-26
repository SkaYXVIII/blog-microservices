import React, {useState} from "react";
import API from "../fetch/axiosSetup";

const CommentCreate = ({postId, fetchPosts}) => {
    const [content, setContent] = useState("");
    const onSubmit = async (event) => {
        event.preventDefault();
        await API.post(`/comments-service/comments/${postId}`, {
            content
        });
        setContent("");
        await fetchPosts();
    }
    return <div>
        <form onSubmit={onSubmit} className="form-group">
            <label>New Comment</label>
            <input value={content} onChange={e => setContent(e.target.value)} className="form-control"/>
            <button className="btn btn-primary">Submit</button>
        </form>
    </div>;
}

export default CommentCreate;