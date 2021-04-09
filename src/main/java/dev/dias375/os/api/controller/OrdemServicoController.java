package dev.dias375.os.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.dias375.os.api.model.OrdemServicoInputModel;
import dev.dias375.os.api.model.OrdemServicoRepresentationModel;
import dev.dias375.os.domain.model.OrdemServico;
import dev.dias375.os.domain.repository.OrdemServicoRepository;
import dev.dias375.os.domain.service.GestaoOrdemServicoService;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServico;

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdemServicoRepresentationModel criar(@Valid @RequestBody OrdemServicoInputModel ordemServicoInputModel) {
		OrdemServico ordemServico = toEntity(ordemServicoInputModel);
		return toModel(gestaoOrdemServico.criar(ordemServico));
	}

	@GetMapping
	public List<OrdemServicoRepresentationModel> listar() {
		return toCollectionModel(ordemServicoRepository.findAll());
	}

	@GetMapping("/{ordemServicoId}")
	public ResponseEntity<OrdemServicoRepresentationModel> buscar(@PathVariable Long ordemServicoId) {
		Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(ordemServicoId);
		if (ordemServico.isPresent()) {
			OrdemServicoRepresentationModel model = toModel(ordemServico.get());
			return ResponseEntity.ok(model);
		}
		return ResponseEntity.notFound().build();
	}

	private OrdemServicoRepresentationModel toModel(OrdemServico ordemServico) {
		return modelMapper.map(ordemServico, OrdemServicoRepresentationModel.class);
	}

	private List<OrdemServicoRepresentationModel> toCollectionModel(List<OrdemServico> ordensServico) {
		return ordensServico.stream().map(ordemServico -> toModel(ordemServico)).collect(Collectors.toList());
	}

	private OrdemServico toEntity(OrdemServicoInputModel input) {
		return modelMapper.map(input, OrdemServico.class);

	}
}
