package com.vee.algorithms.interview;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.naming.OperationNotSupportedException;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

/**
 * 

import java.util.Date;
public class Todo {
    private Map<String, ToDoObject> map = new TreeMap<>(new Comparator() {
        @Override
        public int compare(ToDoObject t1, ToDoObject t2) {
            return t1.dueDate - t2.dueDate;
        }
    }
    
    public Collection<TodoObject> list() {
        map.forEach((k, v) -> System.out.println(v));
    }
    
    public void markDone(String uuid) throw NotFoundException, IllegalOperationException {
        ToDoObject todo = map.get(uuid);
        if (todo != null) {
            if (!todo.done) {
                todo.done = true;
            } else {
                throw new IllegalOperationException();
            }
        } else {
            throw new NotFoundException();
        }
    }
    
    public String add(String title, String desc, Date dueDate, String url) {
        //check maybe url is of right format
        //check for localDate for later
        ToDoObject todo = new ToDoObject(title, desc, dueDate, url);
        map.put(todo.uuid, todo);
        return todo.uuid;
    }
    
    @Test
    public void testOneEntryAndMarkAsDoneFalse() {
        String word = "abc";
        add(word, word, new Date(), "http://www.abc.com");
        Collection<TodoObject> list = list();
        TodoObject todo = list.get(0);
        assert(list.size(), 1);
        assert(todo.done,  true);
    }
    
    @Test
    public void testMarkAsDoneTrue() {
        String word = "abc";
        String uuid = add(word, word, new Date(), "http://www.abc.com");
        markDone(uuid)
        Collection<TodoObject> list = list();
        TodoObject todo = list.get(0);
        assert(todo.done,  true);
    }
    
    @Test
    public void testListSort() {
        String word = "abc";
        String uuid1 = add(word, word, new Date(), "http://www.abc.com");
        String uuid2 = add(word, word, new Date(), "http://www.abc.com");
        markDone(uuid1)
        Collection<TodoObject> list = list();
        TodoObject todo1 = list.get(0);
        TodoObject todo2 = list.get(1);
        assert(todo1.done,  false);
        assert(todo2.done,  true);
    }
    
    @Test(expected = NotFoundException.class)
    public void testMarkAsDone() {
        String word = "abc";
        String uuid = add(word, word, new Date(), "http://www.abc.com");
        markDone("123");
    }
    
    @Test(expected = IllegalOperationException.class)
    public void testMarkAsDone() {
        String word = "abc";
        String uuid = add(word, word, new Date(), "http://www.abc.com");
        markDone(uuid);
        markDone(uuid); // throws the exception;
    }
    
    public static void main(String args[]) {
        Todo todo = new Todo();
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor(new BatchDueDateChecker(todo.map), 1000, 1 * 60, TimeUnit.SECONDS);
    }
    
}

class ToDoObject implements Comparable {
    final String uuid;
    final String title;
    final String desc;
    final Date dueDate;
    final String url;
    boolean done;
    
    @Override
    public int compareTo(ToDoObject o2) {
        return o2.done ? -1; 1;
    }

    public ToDoObject(String title, String desc, Date dueDate, String url) {
        this.uuid = UUID.uuid.toString();
        this.title = title;
        this.desc = desc;
        this.dueDate = dueDate;
        this.url = url;
    }
    
    @Override
    public String toString() {
        return String.format("%s %s %s %s %s, done = %s", uuid, title, desc, dueDate, url, done);
    }
    
}

class BatchDueDateChecker implements Runnable() {
    Map map;

    public BatchDueDateChecker(Map map) {
        this.map = map;
    }
    
    @Override
    public void run() {
        for (Map.Entry entry: map.entrySet()) {
            TodoObject t = entry.getValue();
            if (dueDate() >= new Date()) {
                break;
            }
            get(t.url);
        }
    }
    
    private void get(String url) {
        HttpClient.get(url);
    }
}
 *
 */

//interview = thumbtack

public class ToDos {
	private static ObjectMapper mapper = new ObjectMapper(); 
	Map<String, ToDo> map = new HashMap<>();
	long now = System.currentTimeMillis();
	
	public ToDos() {
		Runnable runnable = () -> {
			for (ToDo t : map.values().stream().filter(x -> !x.done).collect(Collectors.toList())) {
				if (Instant.now().isAfter(t.dueDate.toInstant())) {
					try {
						markAsDone(t.id);
						System.out.println(getToDo(t.id));
					} catch (NoSuchElementException | OperationNotSupportedException | JsonProcessingException e) {
						e.printStackTrace();
					}
				}
			}
		};
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);
	}

	public String getToDo(String id) throws NoSuchElementException, JsonProcessingException {
		ToDo todo = Optional.ofNullable(map.get(id)).orElseThrow(() -> new NoSuchElementException("Can't find any task with id " + id));
		return mapper.writeValueAsString(todo);
	}
	
	public String listPendingToDos() throws JsonProcessingException {
		return mapper.writeValueAsString(
				map.values()
				.stream()
				.filter(t -> !t.done)
				.sorted()
				.collect(Collectors.toList()));
	}
	
	public String listToDos() throws JsonProcessingException {
		List<ToDo> t = map.values()
		.stream()
		.sorted()
		.collect(Collectors.toList());
		String s=  mapper.writeValueAsString(t);
		return s;
	}
	
	public void markAsDone(String id) throws NoSuchElementException, OperationNotSupportedException {
		ToDo todo = Optional.ofNullable(map.get(id)).orElseThrow(() -> new NoSuchElementException("Can't find any task with id " + id));
		if (todo.done) {
			throw new OperationNotSupportedException("Task with id " + id + " is already marked done");
		} else {
			todo.markAsDone();
		}
	}
	
	public String add(String title) {
		ToDo todo = new ToDo(title, Date.from(Instant.now().plusSeconds(15 + new Random().nextInt(30))));
		String id = todo.id;
		map.put(id, todo);
		return id;
	}

	public static void main(String args[]) {
		String msg = "";
		msg += "Please enter one of the following options:\n";
		msg += "1. Add a todo with title\n";
		msg += "2. List all todos\n";
		msg += "3. List pending todos\n";
		msg += "4. Mark todo as done\n";
		msg += "Enter any other number to exit";
		System.out.println(msg);
		Scanner scanner = new Scanner(System.in);
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		mapper.setDateFormat(new ISO8601DateFormat());
		mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		boolean validInput = true;
		ToDos todos = new ToDos();
		while(validInput) {
			int key = 0;
			try {
				key = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				validInput = false;
				scanner.close();
				break;
			}
			
			try {
				switch(key) {
					case 1: System.out.println(todos.add(scanner.nextLine()));
							break;
					case 2: System.out.println(todos.listToDos());
							break;
					case 3: System.out.println(todos.listPendingToDos());
							break;
					case 4: todos.markAsDone(scanner.nextLine());
							break;
					default: scanner.close();
							validInput = false;
							break;
				}
			} catch (Exception e) {
				validInput = false;
				System.out.println(e.getMessage());
				scanner.close();
			}
		}
	}
}

class ToDo implements Comparable<ToDo> {
	final String id = UUID.randomUUID().toString();
	final Date createdOn = new Date();
	final Date dueDate;
	final String title;
	boolean done;
	
	public ToDo(String title, Date dueDate) {
		this.title = title;
		this.dueDate = dueDate;
	}
	
	public void markAsDone() {
		this.done = true;
	}

	@Override
	public int compareTo(ToDo t) {
		return createdOn.compareTo(t.createdOn);
	}
}
